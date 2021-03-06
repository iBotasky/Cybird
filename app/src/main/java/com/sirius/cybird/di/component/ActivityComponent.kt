package com.sirius.cybird.di.component

import android.app.Activity
import com.sirius.cybird.di.module.ActivityModule
import com.sirius.cybird.di.scope.ActivityScoped
import com.sirius.cybird.ui.daily.DailyDetailActivity
import com.sirius.cybird.ui.daily.DailyFragment
import com.sirius.cybird.ui.girls.GirlsFragment
import com.sirius.cybird.ui.home.HomeActivity
import com.sirius.cybird.ui.mine.MineFragment
import com.sirius.cybird.ui.movie.detail.MovieDetailActivity
import com.sirius.cybird.ui.movie.detail.MovieDetailContentFragment
import com.sirius.cybird.ui.movie.hot.MovieHotFragment
import com.sirius.cybird.ui.movie.soon.MovieSoonFragment
import com.sirius.cybird.ui.movie.top.MovieTopFragment
import com.sirius.cybird.ui.one.OneDetailActivity
import com.sirius.cybird.ui.one.OneFragment
import com.sirius.cybird.ui.photo.PhotoViewActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = [RepositoryComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun provideActivity(): Activity

    fun inject(activity: HomeActivity)

    fun inject(movieHotFragment: MovieHotFragment)

    fun inject(movieSoonFragment: MovieSoonFragment)

    fun inject(movieTopFragment: MovieTopFragment)

    fun inject(dailyFragment: DailyFragment)

    fun inject(girlsFragment: GirlsFragment)

    fun inject(dailyDetailActivity: DailyDetailActivity)

    fun inject(movieDetailActivity: MovieDetailActivity)

    fun inject(movieDetailContentFragment: MovieDetailContentFragment)

    fun inject(photoViewActivity: PhotoViewActivity)

    fun inject(mineFragment: MineFragment)

    fun inject(oneFragment: OneFragment)

    fun inject(oneDetailActivity: OneDetailActivity)
}