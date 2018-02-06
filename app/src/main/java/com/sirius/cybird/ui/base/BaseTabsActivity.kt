package com.sirius.cybird.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.util.SparseArrayCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.TypedValue
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.sirius.cybird.R
import com.sirius.cybird.module.TabItemData
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import java.lang.ref.WeakReference


open abstract class BaseTabsActivity : BaseActivity() {
    lateinit var mViewPager: ViewPager
    lateinit var mPagerAdapter: MyViewPagerAdapter
    lateinit var mIndicatorDatas: List<TabItemData>
    var mMagicIndicator: MagicIndicator? = null

    abstract fun getTabItems(): List<TabItemData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewPager = findViewById(R.id.id_view_pager)
        mMagicIndicator = findViewById(R.id.id_indicator)

        mPagerAdapter = getPagerAdapter(supportFragmentManager, ArrayList<TabItemData>())
        val pageTransformer = getPageTransformer()
        if (pageTransformer != null) {
            mViewPager.setPageTransformer(false, pageTransformer)
        }

        mViewPager.offscreenPageLimit = getPageLimit()
        mViewPager.adapter = mPagerAdapter

        mViewPager.pageMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                getPageMargin().toFloat(), resources.displayMetrics).toInt()

        setItems(getTabItems())

        if (mMagicIndicator != null) {
            mIndicatorDatas = getTabItems()
            mMagicIndicator?.navigator = getCommonNavicator()
            ViewPagerHelper.bind(mMagicIndicator, mViewPager)
        }
    }

    open fun getCommonNavicator(): CommonNavigator {
        val commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true  // 自适应模式
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return getPageCount()
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
                val simplePagerTitleView = ColorTransitionPagerTitleView(context)
                simplePagerTitleView.setText(mIndicatorDatas[index].titleId)
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.normalColor = context.resources.getColor(R.color.colorPrimaryLight)
                simplePagerTitleView.selectedColor = context.resources.getColor(R.color.textColorPrimary)
                simplePagerTitleView.setOnClickListener { mViewPager.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context): IPagerIndicator {
                val indicator = LinePagerIndicator(context)
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(1.6f)
                indicator.lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
                indicator.setColors(context.resources.getColor(R.color.colorAccent))
                return indicator
            }
        }
        return commonNavigator
    }

    fun getPageCount(): Int {
        return mPagerAdapter.count
    }

    private fun setItems(list: List<TabItemData>) {
        mPagerAdapter.addItems(list)

    }

    open fun getPageLimit(): Int {
        return 3
    }

    open fun getPageMargin(): Int {
        return 0
    }

    open fun getPageTransformer(): ViewPager.PageTransformer? {
        return null
    }


    fun getPagerAdapter(fm: FragmentManager, items: List<TabItemData>): MyViewPagerAdapter {
        return MyViewPagerAdapter(this, fm, items)
    }

    inner class MyViewPagerAdapter(private val context: Context, fm: FragmentManager, items: List<TabItemData>) : FragmentPagerAdapter(fm) {
        private val pages: MutableList<TabItemData>
        private val holder: SparseArrayCompat<WeakReference<Fragment>>

        init {
            this.pages = java.util.ArrayList<TabItemData>()
            this.holder = SparseArrayCompat(pages.size)

            addItems(items)
        }


        fun addItems(items: List<TabItemData>) {
            this.pages.addAll(items)
            notifyDataSetChanged()
        }


        fun getTabItem(position: Int): TabItemData {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }

        private fun makeFragmentName(id: Long): String {
            return "android:switcher:" + mViewPager.id + ":" + id
        }

        override fun getItemPosition(ob: Any): Int {
            //// TODO: 2017/5/24
            val tag = (ob as Fragment).tag
            var position = -1
            for (i in pages.indices) {
                val tabTag = makeFragmentName(getItemId(i))
                if (tabTag == tag) {
                    position = i
                    break
                }
            }
            return if (position == -1) PagerAdapter.POSITION_NONE else position
        }

        override fun getItem(position: Int): Fragment {
            return getPagerItem(position).instantiate(context)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val item = super.instantiateItem(container, position)
            if (item is Fragment) {
                holder.put(position, WeakReference(item))
            }
            return item
        }

        override fun destroyItem(container: ViewGroup, position: Int, ob: Any) {
            holder.remove(position)
            super.destroyItem(container, position, ob)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return context.getString(getPagerItem(position).titleId)
        }

        override fun getPageWidth(position: Int): Float {
            return super.getPageWidth(position)
        }

        fun getPage(position: Int): Fragment? {
            val weakRefItem = holder.get(position)
            return weakRefItem?.get()
        }

        protected fun getPagerItem(position: Int): TabItemData {
            return pages[position]
        }

    }
}