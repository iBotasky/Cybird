package com.sirius.cybird.di.model

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 *
 *Create By Botasky 31/12/2017
 **/
class NetModule {
    @Provides
    @Singleton
    @Named("http")
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build()
    }
}