package com.sirius.cybird.ui.movie.soon

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.response.FilmsData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 * Created By Botasky 28/04/2018
 */
class MovieSoonPresenter {
    val mRetrofit: Retrofit

    @Inject
    constructor(@Named(NameConst.DOUBAN) retrofit: Retrofit) {
        mRetrofit = retrofit
    }

    fun getComingSoon(start: Int): Observable<FilmsData> {
        return mRetrofit.create(FilmsApi::class.java)
                .getComingSoon(start,20)
                .flatMap { filmData-> Observable.just(filmData) }
    }
}