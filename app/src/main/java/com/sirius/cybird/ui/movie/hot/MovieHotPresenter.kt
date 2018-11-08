package com.sirius.cybird.ui.movie.hot

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.response.Film
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MovieHotPresenter @Inject constructor(@Named(NameConst.DOUBAN) doubanRetrofit: Retrofit) {
    val mRetrofit: Retrofit = doubanRetrofit

    fun getInTheaters():Observable<List<Film>>{
        return mRetrofit.create(FilmsApi::class.java)
                .getInTheaters()
                .flatMap { filmsData -> Observable.just(filmsData.films)}
    }
}