package com.sirius.cybird.ui.home

import android.os.Bundle
import android.util.Log
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.db.FilmEntity
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.url.Urls
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseNavActivity
import com.sirius.cybird.ui.movie.MovieFragment
import com.sirius.cybird.utils.ToastUtils
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject


class HomeActivity : BaseNavActivity() {
    private var mCurrentBackPressedTime = 0L
    private val BACK_PRESSED_INTERVAL = 2000L
    lateinit var mHomeBinding: ActivityHomeBinding
    lateinit var mTitleResources: List<Int>

    @Inject
    lateinit var mRetrofit: Retrofit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()

        mActivityComponent.inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        setToolbarTitle(mTitleResources[0])

        mRetrofit.create(FilmsApi::class.java)
                .getInTheaters(Urls.DOUBAN_FILM_URL_HOST + "movie/in_theaters")
                .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe({ film ->
                    val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())

                    Log.e("TAG", "put success " + filmEntity.id)
                }, { e ->
                    Log.e("TAG", e.message)
                })
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }


    override fun getBottomNavDatas(): List<NavItemData> {
        mTitleResources = listOf(R.string.tab_movie, R.string.tab_daily, R.string.tab_girls, R.string.tab_mine)
        return listOf(
                NavItemData(BottomNavigationItem(R.mipmap.ic_movie, R.string.tab_movie).setActiveColorResource(R.color.color_movie), MovieFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_book, R.string.tab_daily).setActiveColorResource(R.color.color_daily), MovieHotFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), MovieHotFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_mine), MovieHotFragment::class.java)
        )
    }

    override fun onNavigationChange(position: Int) {
        setToolbarTitle(mTitleResources[position])
    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - mCurrentBackPressedTime >= BACK_PRESSED_INTERVAL) {
            mCurrentBackPressedTime = System.currentTimeMillis()
            ToastUtils.show(R.string.g_back_pressed_exit)
        } else {
            finish()
        }
    }
}