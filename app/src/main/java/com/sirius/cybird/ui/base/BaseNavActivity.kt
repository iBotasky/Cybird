package com.sirius.cybird.ui.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.util.SparseArrayCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.module.NavItemData
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


open abstract class BaseNavActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    lateinit var mViewPager: ViewPager
    lateinit var mPagerAdapter: MyViewPagerAdapter
    lateinit var mBottomNavBar: BottomNavigationBar

    abstract fun getBottomNavDatas(): List<NavItemData>


    override fun setupViews() {
        super.setupViews()
        mBottomNavBar = findViewById(R.id.id_bottom_nav_bar)
        mBottomNavBar.setMode(getBottomNavMode())
        mBottomNavBar.setBackgroundStyle(getBottomNavBackgroundStyle())
        mBottomNavBar.setTabSelectedListener(this)

        mViewPager = findViewById(R.id.id_view_pager)
        mPagerAdapter = getPagerAdapter(supportFragmentManager, ArrayList<NavItemData>())
        val pageTransformer = getPageTransformer()
        if (pageTransformer != null) {
            mViewPager.setPageTransformer(false, pageTransformer)
        }
        mViewPager.adapter = mPagerAdapter
        mViewPager.pageMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                getPageMargin().toFloat(), resources.displayMetrics).toInt()
        mViewPager.addOnPageChangeListener(this)
        setItems(getBottomNavDatas())
        mViewPager.offscreenPageLimit = getPageCount()
    }

    open fun getBottomNavMode(): Int {
        return BottomNavigationBar.MODE_FIXED
    }

    open fun getBottomNavBackgroundStyle(): Int {
        if (Build.VERSION.SDK_INT >= 21) return BottomNavigationBar.BACKGROUND_STYLE_STATIC else return BottomNavigationBar.BACKGROUND_STYLE_DEFAULT
    }

    //NavTabListener
    override fun onTabSelected(position: Int) {
        mViewPager.currentItem = position
    }

    override fun onTabReselected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {

    }
    //NavTabListener END

    //ViewPagerListener
    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mBottomNavBar.selectTab(position)

    }
    //ViewPagerListener END


    fun getPageCount(): Int {
        return mPagerAdapter.count
    }


    private fun setItems(list: List<NavItemData>) {
        mPagerAdapter.addItems(list)
        for (nav in list) {
            mBottomNavBar.addItem(nav.item)
        }
        mBottomNavBar.initialise()
    }

    open fun getPageMargin(): Int {
        return 0
    }

    open fun getPageTransformer(): ViewPager.PageTransformer? {
        return null
    }


    private fun getPagerAdapter(fm: FragmentManager, items: List<NavItemData>): MyViewPagerAdapter {
        return MyViewPagerAdapter(this, fm, items)
    }

    inner class MyViewPagerAdapter(private val context: Context, fm: FragmentManager, items: List<NavItemData>) : FragmentPagerAdapter(fm) {
        private val pages: MutableList<NavItemData>
        private val holder: SparseArrayCompat<WeakReference<Fragment>>

        init {
            this.pages = java.util.ArrayList<NavItemData>()
            this.holder = SparseArrayCompat(pages.size)

            addItems(items)
        }


        fun addItems(items: List<NavItemData>) {
            this.pages.addAll(items)
            notifyDataSetChanged()
        }


        fun getTabItem(position: Int): NavItemData {
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
//        override fun getPageTitle(position: Int): CharSequence? {
//            return context.getString(getPagerItem(position).titleId)
//        }

        override fun getPageWidth(position: Int): Float {
            return super.getPageWidth(position)
        }

        fun getPage(position: Int): Fragment? {
            val weakRefItem = holder.get(position)
            return weakRefItem?.get()
        }

        protected fun getPagerItem(position: Int): NavItemData {
            return pages[position]
        }

    }
}