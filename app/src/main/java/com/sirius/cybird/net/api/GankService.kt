package com.sirius.cybird.net.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */
interface GankService {
    @GET("data/福利/20/{index}")
    fun getGirls(@Path("index") index: Int): Observable<GirlsResponse>
}