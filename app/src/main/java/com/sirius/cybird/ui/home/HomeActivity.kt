package com.sirius.cybird.ui.home

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.ui.base.BaseNavActivity
import com.sirius.cybird.ui.movie.MovieFragment
import com.sirius.cybird.ui.movie.hot.MovieHotFragment
import com.sirius.cybird.utils.ToastUtils


class HomeActivity : BaseNavActivity() {
    private var mCurrentBackPressedTime = 0L
    private val BACK_PRESSED_INTERVAL = 2000L
    lateinit var mHomeBinding: ActivityHomeBinding
    lateinit var mTitleResources: List<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
        mActivityComponent.inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        setToolbarTitle(mTitleResources[0])
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

    override fun initializeInjector() {

    }
}