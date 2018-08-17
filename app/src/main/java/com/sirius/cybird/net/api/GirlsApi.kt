package com.sirius.cybird.net.api

import com.sirius.cybird.net.response.GirlResopnse
import com.sirius.cybird.net.url.Urls
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */
interface GirlsApi {
    @GET("api/data/福利/20/{index}")
    fun accessGirls(@Path("index") index: Int): Observable<GirlResopnse>

    companion object {
        fun getGirlsService(): GirlsApi {
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Urls.HOST_GANK_GIRLS)
                    .build()
                    .create(GirlsApi::class.java)
        }
    }
}