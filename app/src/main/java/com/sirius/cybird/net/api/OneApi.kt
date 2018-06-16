package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.net.response.OneHomeData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface OneApi {
    @GET("/api/onelist/idlist")
    fun getOneListId(): Observable<OneHomeData>

    @GET("/api/onelist/{id}/0")
    fun getOneDetailById(@Path("id") id: String): Observable<OneDetailData>
}