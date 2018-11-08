package com.sirius.cybird.ui.movie.top

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.response.FilmsData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MovieTopPresenter @Inject constructor(@Named(NameConst.DOUBAN) retrofit: Retrofit) {
    val mRetrofit: Retrofit = retrofit

    fun getTopFilms(start: Int): Observable<FilmsData> {
        return mRetrofit.create(FilmsApi::class.java)
                .getTop250(start,20)
                .flatMap { filmData-> Observable.just(filmData) }
    }
}