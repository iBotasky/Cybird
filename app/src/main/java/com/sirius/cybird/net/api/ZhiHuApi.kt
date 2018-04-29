package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.ZhiHuData
import com.sirius.cybird.net.url.Urls
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 *Create By Botasky 31/12/2017
 **/
interface ZhiHuApi {
    @GET("/api/4/news/latest")
    fun getLastNews()

    @GET("/api/4/news/{id}")
    fun getNewsDetail(@Path("id") id: Int) :Observable<ZhiHuData>

}