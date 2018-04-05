package com.sirius.cybird.di.model

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.sirius.cybird.di.qualifier.ForApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule {
    var mApplication: Application

    constructor(application: Application){
        this.mApplication = application
    }

    @ForApplication
    @Singleton
    @Provides
    fun provideApplication(): Application{
        return this.mApplication
    }

    @ForApplication
    @Singleton
    @Provides
    fun provideContext():Context{
        return this.mApplication.applicationContext
    }

}