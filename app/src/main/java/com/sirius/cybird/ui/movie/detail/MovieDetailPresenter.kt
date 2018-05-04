package com.sirius.cybird.ui.movie.detail

import com.chad.library.adapter.base.entity.MultiItemEntity
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

    @Inject
    constructor(@Named(NameConst.DOUBAN) retrofit: Retrofit) {
        mRetrofit = retrofit
    }

    fun getFilmDetail(id: String): Observable<List<MultiItemEntity>> {
        return mRetrofit.create(FilmsApi::class.java)
                .getFilmDetail(id)
                .flatMap { filmDetailData -> Observable.just(convertToMultiEntity(filmDetailData)) }
    }

    private fun convertToMultiEntity(data: FilmDetailData): List<MultiItemEntity> {
        val multiList: MutableList<MultiItemEntity> = mutableListOf()
        multiList.add(DetailHead(title = data.title, originalTitle = data.originalTitle, year = data.year, ratingCount = data.ratingsCount, rating = data.rating))
        multiList.add(DetailCasts(directors = data.directors, casts = data.casts))
        multiList.add(DetailSummary(summary = data.summary))
        return multiList
    }
}