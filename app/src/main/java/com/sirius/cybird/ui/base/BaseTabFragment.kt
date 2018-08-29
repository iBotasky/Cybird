package com.sirius.cybird.ui.base

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.util.SparseArrayCompat
import android.support.v4.view.ViewPager
import android.util.TypedValue
import android.view.ViewGroup
import com.sirius.cybird.R
import com.sirius.cybird.module.TabItemData
import java.lang.ref.WeakReference

abstract class BaseTabFragment : BaseLazyFragment() {
    lateinit var mTabLayout: TabLayout
    lateinit var mViewPager: ViewPager
    lateinit var mPagerAdapter: TabViewPagerAdapter
    lateinit var mTabDatas: MutableList<TabItemData>

    override fun setupViews() {
        super.setupViews()
        mTabLayout = mBinding.root.findViewById(R.id.id_tab_bar)
        mViewPager = mBinding.root.findViewById(R.id.id_view_pager)

        mPagerAdapter = getPagerAdapter(childFragmentManager, mutableListOf())
        val pageTransformer = getPageTransformer()
        if (pageTransformer != null) {
            mViewPager.setPageTransformer(false, pageTransformer)
        }

        mViewPager.adapter = mPagerAdapter
        mViewPager.pageMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getPageMargin().toFloat(), resources.displayMetrics).toInt()
//        setItems(getTabItems())
        mViewPager.offscreenPageLimit = getPageLimit()
        mTabLayout.setupWithViewPager(mViewPager)
    }


    abstract fun getTabItems(): List<TabItemData>

    open fun getPageLimit(): Int {
        return mPagerAdapter.count
    }

    private fun getPagerAdapter(fm: FragmentManager, items: List<TabItemData>): TabViewPagerAdapter {
        return TabViewPagerAdapter(this.activity!!, fm, items)
    }

    open fun getPageTransformer(): ViewPager.PageTransformer? {
        return null
    }

    open fun getPageMargin(): Int {
        return 0
    }

    private fun setItems(list: List<TabItemData>) {
        mPagerAdapter.addItems(list)
    }

    //懒加载实现
    override fun loadData() {
        setItems(getTabItems())
    }

    inner class TabViewPagerAdapter(val context: Context, fm: FragmentManager, tabItems: List<TabItemData>) : FragmentPagerAdapter(fm) {
        private val pages: MutableList<TabItemData>
        private val holder: SparseArrayCompat<WeakReference<Fragment>>

        init {
            this.pages = mutableListOf()
            this.holder = SparseArrayCompat(pages.size)
            addItems(tabItems)
        }


        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val item = super.instantiateItem(container, position)
            if (item is Fragment) {
                holder.put(position, WeakReference(item))
            }
            return item
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return context.getString(pages[position].titleId)
        }

        fun getPage(position: Int): Fragment? {
            val weakRefItem = holder.get(position)
            return weakRefItem?.get()
        }

        fun addItems(list: List<TabItemData>) {
            this.pages.addAll(list)
            notifyDataSetChanged()
        }

        override fun getItem(position: Int): Fragment {
            return this.pages[position].instantiate(context)
        }

        override fun getCount(): Int {
            return this.pages.size
        }
    }
}