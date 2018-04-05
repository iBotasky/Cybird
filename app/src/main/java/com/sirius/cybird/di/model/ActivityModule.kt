package com.sirius.cybird.di.model

import android.app.Activity
import com.google.gson.Gson
import com.sirius.cybird.di.scope.ActivityScoped
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
class ActivityModule {
    private val mActivity: Activity
    constructor(activity: Activity){
        mActivity = activity
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @ActivityScoped
    fun activity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityScoped
    fun provideName():String {
        return "Botasky"
    }
}