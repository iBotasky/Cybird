package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.FilmsData
import com.sirius.cybird.net.url.Urls
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description： 豆瓣API
 * Created by Botasky on 2017/12/29.
 */
interface FilmsApi {
    @GET("v2/movie/in_theaters")
    fun getInTheaters(): Observable<FilmsData>

    @GET("v2/movie/coming_soon")
    fun getComingSoon(@Query("start") start: Int, @Query("count") count: Int): Observable<FilmsData> //Get用参数Query来做

    @GET("v2/movie/top250")
    fun getTop250(@Query("start") start: Int, @Query("count") connt: Int): Observable<FilmsData>

    companion object {
        fun getFilmsService(): FilmsApi {
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Urls.DOUBAN_FILM_URL_HOST)
                    .build()
                    .create(FilmsApi::class.java)
        }
    }
}