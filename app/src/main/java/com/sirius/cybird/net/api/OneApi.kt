package com.sirius.cybird.net.api

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface OneApi {
    @GET("/api/onelist/idlist")
    fun getOneListId():Observable<Response<Void>>
}