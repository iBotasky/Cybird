package com.sirius.cybird.di.model

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sirius.cybird.di.qualifier.ForApplication
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 *Create By Botasky 31/12/2017
 **/
@Module
class NetModule {
    val mBaseUrl: String
    constructor(baseUrl: String){
        this.mBaseUrl = baseUrl
    }

    @Provides
    @Singleton
    internal fun provideHttpCache(@ForApplication application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        // log用拦截器
        val logging = HttpLoggingInterceptor()

//        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
//        if (SystemConfig.DEBUG_MODE) {
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        } else {
//            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
//        }


        val client = OkHttpClient.Builder()
//                .addInterceptor(HeadInterceptor())
                .addInterceptor(logging)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

        client.cache(cache)
        return client.build()
    }


    @Provides
    @Singleton
    @Named("http")//用以区分调用那个retrofit
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
    }
}