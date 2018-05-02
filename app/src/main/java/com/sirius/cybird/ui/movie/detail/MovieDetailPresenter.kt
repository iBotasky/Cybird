package com.sirius.cybird.ui.movie.detail

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.FilmsApi
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

    fun getFilmDetail(id: String): Observable<FilmDetailData> {
        return mRetrofit.create(FilmsApi::class.java)
                .getFilmDetail(id)
    }
}