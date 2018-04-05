package com.sirius.cybird.di.component

import android.app.Activity
import com.sirius.cybird.di.model.ActivityModule
import com.sirius.cybird.di.scope.ActivityScoped
import com.sirius.cybird.repository.RepositoryComponent
import com.sirius.cybird.ui.home.HomeActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = arrayOf(RepositoryComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity

    fun inject(activity: HomeActivity)
}