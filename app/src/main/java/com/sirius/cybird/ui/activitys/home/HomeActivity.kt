package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import com.sirius.cybird.R
import com.sirius.cybird.module.TabItemData
import com.sirius.cybird.ui.base.BaseTabsActivity

class HomeActivity: BaseTabsActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun getIndicatorDatas(): List<TabItemData> {
        val tabItems = ArrayList<TabItemData>()
        tabItems.add(TabItemData(R.string.app_name, TestFragment::class.java))
        tabItems.add(TabItemData(R.string.app_home, TestFragment::class.java))
        return tabItems
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }
}