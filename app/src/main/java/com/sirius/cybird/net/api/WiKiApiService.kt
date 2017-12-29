package com.sirius.cybird.net.api

import com.sirius.cybird.data.SearchResultModel
import com.sirius.cybird.net.url.Urls
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Description：
 * Created by Botasky on 2017/12/29.
 */
interface WiKiApiService {
    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<SearchResultModel.Result>

    companion object {
        fun getWikiService():WiKiApiService{
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Urls.WIKI_URL)
                    .build()
                    .create(WiKiApiService::class.java)
        }
    }
}