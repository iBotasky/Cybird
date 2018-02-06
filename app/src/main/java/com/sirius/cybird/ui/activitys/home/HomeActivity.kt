package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.TabItemData
import com.sirius.cybird.ui.activitys.film.FilmFragment
import com.sirius.cybird.ui.base.BaseTabsActivity

class HomeActivity: BaseTabsActivity() {
    lateinit var mHomeBinding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
    }


    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }

    override fun getTabItems(): List<TabItemData> {
        val tabItems = ArrayList<TabItemData>()
        tabItems.add(TabItemData(R.string.tab_film, FilmFragment::class.java))
        tabItems.add(TabItemData(R.string.tab_daily, Test2Fragment::class.java))
        tabItems.add(TabItemData(R.string.tab_girls, Test2Fragment::class.java))
        return tabItems
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.sample_actions, menu)
//        return true
//    }
}