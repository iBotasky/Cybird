package com.sirius.cybird.ui.home

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.ui.movie.MovieFragment
import com.sirius.cybird.ui.base.BaseNavActivity

class HomeActivity: BaseNavActivity() {

    lateinit var mHomeBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
    }

    override fun setupViews() {
        super.setupViews()
        setToolbarTitle(R.string.app_name)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }


    override fun getBottomNavDatas(): List<NavItemData> {
        val navDatas = ArrayList<NavItemData>()
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_movie, R.string.tab_movie).setActiveColorResource(R.color.color_movie), MovieFragment::class.java))
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_book,R.string.tab_daily).setActiveColorResource(R.color.color_daily), Test2Fragment::class.java))
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), Test2Fragment::class.java))
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_mine), Test2Fragment::class.java))
        return navDatas
    }

}