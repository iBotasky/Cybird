package com.sirius.cybird.di.model

import android.app.Application
import android.content.Context
import com.sirius.cybird.di.qualifier.ForApplication
import dagger.Module
import dagger.Provides
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