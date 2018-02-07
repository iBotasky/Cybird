package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.ui.activitys.movie.MovieFragment
import com.sirius.cybird.ui.base.BaseNavActivity

class HomeActivity: BaseNavActivity() {

    lateinit var mHomeBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
    }


    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }

    override fun getBottomNavDatas(): List<NavItemData> {
        val navDatas = ArrayList<NavItemData>()
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_movie, R.string.tab_movie), MovieFragment::class.java))
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot,R.string.tab_girls), Test2Fragment::class.java))
        navDatas.add(NavItemData(BottomNavigationItem(R.mipmap.ic_book, R.string.tab_daily), Test2Fragment::class.java))
        return navDatas
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.sample_actions, menu)
//        return true
//    }
}