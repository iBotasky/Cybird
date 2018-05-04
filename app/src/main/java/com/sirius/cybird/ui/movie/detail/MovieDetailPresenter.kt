package com.sirius.cybird.ui.movie.detail

import android.app.Activity
import android.content.Context
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.response.DetailCasts
import com.sirius.cybird.net.response.DetailHead
import com.sirius.cybird.net.response.DetailSummary
import com.sirius.cybird.net.response.FilmDetailData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Created By Botasky 02/05/2018
 */
class MovieDetailPresenter {
    private val mRetrofit: Retrofit
    private val mContext: Context
    @Inject
    constructor(@Named(NameConst.DOUBAN) retrofit: Retrofit, activity: Activity) {
        mRetrofit = retrofit
        mContext = activity
    }

    fun getFilmDetail(id: String): Observable<List<MultiItemEntity>> {
        return mRetrofit.create(FilmsApi::class.java)
                .getFilmDetail(id)
                .flatMap { filmDetailData -> Observable.just(convertToMultiEntity(filmDetailData)) }
    }

    private fun convertToMultiEntity(data: FilmDetailData): List<MultiItemEntity> {
        val multiList: MutableList<MultiItemEntity> = mutableListOf()
        var yearAndTag:String = ""
        yearAndTag += data.year +" / "
        for (tag in data.genres){
            if (data.genres.indexOf(tag) != data.genres.size - 1)
                yearAndTag += tag + " / "
            else
                yearAndTag += tag
        }

        multiList.add(DetailHead(title = data.title, originalTitle = mContext.getString(R.string.movie_original_title) + data.originalTitle, year = yearAndTag, ratingCount = data.ratingsCount, rating = data.rating))
        multiList.add(DetailSummary(summary = data.summary))
        multiList.add(DetailCasts(directors = data.directors, casts = data.casts))
        return multiList
    }
}