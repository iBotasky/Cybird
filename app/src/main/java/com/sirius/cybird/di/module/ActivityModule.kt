package com.sirius.cybird.di.module

import android.app.Activity
import com.sirius.cybird.di.scope.ActivityScoped
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
class ActivityModule constructor(activity: Activity) {
    private val mActivity: Activity = activity

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