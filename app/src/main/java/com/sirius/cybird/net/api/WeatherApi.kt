package com.sirius.cybird.net.api

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 *Create By Botasky 31/12/2017
 **/
interface WeatherApi {
    @GET("/onebox/weather/query")
    fun getWeather(@Query("cityname") city: String, @Query("key") key: String): Observable<JsonObject>
}