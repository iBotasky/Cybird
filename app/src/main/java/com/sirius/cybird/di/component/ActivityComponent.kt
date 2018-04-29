package com.sirius.cybird.di.component

import android.app.Activity
import com.sirius.cybird.di.module.ActivityModule
import com.sirius.cybird.di.scope.ActivityScoped
import com.sirius.cybird.repository.RepositoryComponent
import com.sirius.cybird.ui.daily.DailyFragment
import com.sirius.cybird.ui.home.HomeActivity
import com.sirius.cybird.ui.movie.hot.MovieHotFragment
import com.sirius.cybird.ui.movie.soon.MovieSoonFragment
import com.sirius.cybird.ui.movie.top.MovieTopFragment
import dagger.Component

@ActivityScoped
@Component(dependencies = arrayOf(RepositoryComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity

    fun inject(activity: HomeActivity)

    fun inject(movieHotFragment: MovieHotFragment)

    fun inject(movieSoonFragment: MovieSoonFragment)

    fun inject(movieTopFragment: MovieTopFragment)

    fun inject(dailyFragment: DailyFragment)
}