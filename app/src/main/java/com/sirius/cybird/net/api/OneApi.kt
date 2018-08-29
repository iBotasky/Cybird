package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.net.response.OneHomeData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface OneApi {

    @GET("/api/channel/one/0/0")///api/channel/one/{time 2018-08-29}/0
    fun getOneDetail():Observable<OneDetailData>
}