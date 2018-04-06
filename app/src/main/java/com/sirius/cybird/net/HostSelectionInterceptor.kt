package com.sirius.cybird.net

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * 修改Host的拦截器，目前已知问题是同时请求两个不同Host，因为同步原因，导致host没被修改
 */
class HostSelectionInterceptor : Interceptor {
    @Volatile private var host: HttpUrl? = null

    fun setHost(url: String) {
        this.host = HttpUrl.parse(url)
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = host?.let {
            val newUrl = chain.request().url().newBuilder()
                    .scheme(it.scheme())
                    .host(it.url().toURI().host)
                    .port(it.port())
                    .build()

            return@let chain.request().newBuilder()
                    .url(newUrl)
                    .build()
        }

        return chain.proceed(newRequest)
    }
}