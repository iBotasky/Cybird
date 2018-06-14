package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.OneHomeData
import io.reactivex.Observable
import retrofit2.http.GET

interface OneApi {
    @GET("/api/onelist/idlist")
    fun getOneListId(): Observable<OneHomeData>
}