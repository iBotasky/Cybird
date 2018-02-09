package com.sirius.cybird.ui.home

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.ui.movie.MovieFragment
import com.sirius.cybird.ui.base.BaseNavActivity

class HomeActivity : BaseNavActivity() {

    lateinit var mHomeBinding: ActivityHomeBinding
    lateinit var mTitleResources: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
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
                NavItemData(BottomNavigationItem(R.mipmap.ic_book, R.string.tab_daily).setActiveColorResource(R.color.color_daily), Test2Fragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), Test2Fragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_mine), Test2Fragment::class.java)
        )
    }

    override fun onNavigationChange(position: Int) {
        setToolbarTitle(mTitleResources[position])
    }

}