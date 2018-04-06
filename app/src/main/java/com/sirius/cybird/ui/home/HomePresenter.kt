package com.sirius.cybird.ui.home

import android.util.Log
import com.google.gson.Gson
import com.sirius.cybird.db.FilmEntity
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.url.Urls
import com.sirius.cybird.rx.TransformScheduler
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject

class HomePresenter {
    val mRetrofit: Retrofit
    val mGson: Gson

    @Inject
    constructor(retrofit: Retrofit, gson: Gson) {
        mRetrofit = retrofit
        mGson = gson
    }

    fun getTime() {
        mRetrofit.create(FilmsApi::class.java)
                .getInTheaters(Urls.DOUBAN_FILM_URL_HOST + "movie/in_theaters")
                .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { film ->
                            val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())

                            Log.e("TAG", "put success " + mGson.toJson(filmEntity))
                        },
                        { e ->
                            Log.e("TAG", e.message)
                        })
    }
}