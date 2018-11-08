package com.sirius.cybird.di.module

import android.app.Application
import android.content.Context
import com.sirius.cybird.di.qualifier.ForApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule constructor(application: Application) {
    val mApplication: Application = application

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