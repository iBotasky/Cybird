package com.sirius.cybird.ui.base

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.sirius.cybird.R
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


open class BasePagesActivity : BaseActivity() {
    lateinit var mViewPager: ViewPager
    lateinit var mMagicIndicator: MagicIndicator
    lateinit var mIndicatorDatas: List<IndicatorData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewPager = findViewById(R.id.id_view_pager)
        mMagicIndicator = findViewById(R.id.id_indicator)
        mIndicatorDatas = getIndicatorDatas()
        mMagicIndicator.navigator = getCommonNavicator()
        ViewPagerHelper.bind(mMagicIndicator,mViewPager)
    }


    open fun getCommonNavicator(): CommonNavigator {
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.normalColor = Color.GRAY
                colorTransitionPagerTitleView.selectedColor = Color.BLACK
                colorTransitionPagerTitleView.text = mIndicatorDatas[index].title
                colorTransitionPagerTitleView.setOnClickListener({
                    mViewPager.currentItem = index
                })
                return colorTransitionPagerTitleView
            }

            override fun getCount(): Int {
                return mIndicatorDatas.size
            }

            override fun getIndicator(p0: Context?): IPagerIndicator {
                val indicator = LinePagerIndicator(this@BasePagesActivity)
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                return indicator
            }
        }
        return commonNavigator
    }


    open fun getIndicatorDatas(): List<IndicatorData> {
        return ArrayList<IndicatorData>()
    }
}