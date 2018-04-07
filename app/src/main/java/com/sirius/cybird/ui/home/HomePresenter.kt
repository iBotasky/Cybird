package com.sirius.cybird.ui.home

import android.util.Log
import com.sirius.cybird.db.FilmEntity
import com.sirius.cybird.net.HostSelectionInterceptor
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.api.GirlsApi
import com.sirius.cybird.net.url.Urls
import com.sirius.cybird.rx.TransformScheduler
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class HomePresenter {
//    val mRetrofit: Retrofit
//    val mHost: HostSelectionInterceptor

    val mDoubanRetrofit: Retrofit
    val mGankRetrofit: Retrofit

    @Inject
    constructor(@Named("douban") doubanRetrofit: Retrofit, @Named("gank") gankRetrofit: Retrofit) {
//        mRetrofit = retrofit
//        mHost = host
        mDoubanRetrofit = doubanRetrofit
        mGankRetrofit = gankRetrofit
    }

    fun getFilms() {
        /**
         * 这个写法只能同步
         */
//        mHost.setHost(Urls.DOUBAN_FILM_URL_HOST)
//        mRetrofit.create(FilmsApi::class.java)
//                .getInTheaters()
//                .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
//                .compose(TransformScheduler.applyNewThreadScheduler())
//                .subscribe(
//                        { film ->
//                            val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())
//
//                            Log.e("TAG", "put success " + filmEntity.text)
//                        },
//                        { e ->
//                            Log.e("TAG", e.message)
//                        },
//                        {
//                            mHost.setHost(Urls.GANK_GIRLS_URL_HOST)
//                            mRetrofit.create(GirlsApi::class.java)
//                                    .accessGirls(1)
//                                    .compose(TransformScheduler.applyNewThreadScheduler())
//                                    .subscribe({ girl ->
//                                        Log.e("TAG", "size " + girl.results.size)
//                                    })
//                        })

        mDoubanRetrofit.create(FilmsApi::class.java)
                .getInTheaters()
                .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { film ->
                            val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())

                            Log.e("TAG", "put success " + filmEntity.text)
                        },
                        { e ->
                            Log.e("TAG", e.message)
                        })
    }

    fun getGirls(){
        mGankRetrofit.create(GirlsApi::class.java)
                .accessGirls(1)
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe({ girl ->
                    Log.e("TAG", "size " + girl.results.size)
                })
    }
}