package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.ui.base.BasePagesActivity
import com.sirius.cybird.ui.base.IndicatorData
import java.util.ArrayList

class HomeActivity:BasePagesActivity() {
    lateinit var mHomeBinding :ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
    }

    fun setupPages(){
        val adapter = FragmentPagerAdapter(supportFragmentManager)

//        for (Int in mIndicatorDatas){
//            val fragment = Fragment()
//        }
    }


    override fun getIndicatorDatas(): List<IndicatorData> {
        val indicatorDatas = ArrayList<IndicatorData>()
        indicatorDatas.add(IndicatorData(title = "电影", resouce = 0))
        indicatorDatas.add(IndicatorData(title = "音乐", resouce = 0))
        indicatorDatas.add(IndicatorData(title = "笑话", resouce = 0))
        return indicatorDatas
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }
}