package com.sirius.cybird.di.module

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sirius.cybird.BuildConfig
import com.sirius.cybird.di.NameConst
import com.sirius.cybird.di.qualifier.ForApplication
import com.sirius.cybird.net.HostSelectionInterceptor
import com.sirius.cybird.net.url.Urls
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
    //这个项目中BaseURL需要切换，改用HostSelectionInceptor
//    val mBaseUrl: String
//
//    constructor(baseUrl: String) {
//        this.mBaseUrl = baseUrl
//    }

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
    internal fun provideHostSelection(): HostSelectionInterceptor {
        return HostSelectionInterceptor()
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        // log用拦截器
        val logging = HttpLoggingInterceptor()

        // 开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        val client = OkHttpClient.Builder()
                //.addInterceptor(HeadInterceptor())//Header拦截器
                .addInterceptor(logging)
                //.addInterceptor(selectionInterceptor)//Host修改拦截器
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)

        client.cache(cache)
        return client.build()
    }

    //    @Named("http")//用以区分调用那个retrofit
    @Provides
    @Singleton
    @Named(NameConst.JUHE)
    fun provideJuHeRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.WEATHRE_URL_HOST)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @Named(NameConst.DOUBAN)
    fun provideDoubanRetrofit(gson: Gson, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.DOUBAN_FILM_URL_HOST)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @Named(NameConst.GANK)
    fun provideGankRetrofit(gson: Gson, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.GANK_GIRLS_URL_HOST)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @Named(NameConst.ZHIHU)
    fun provideZhiHuRetrofit(gson: Gson, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.ZHI_HU_DIARY_URL_HOST)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    @Named(NameConst.ONE)
    fun provideOneRetrofit(gson: Gson, okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Urls.ONE_URL_HOST)
                .client(okHttpClient)
                .build()
    }
}