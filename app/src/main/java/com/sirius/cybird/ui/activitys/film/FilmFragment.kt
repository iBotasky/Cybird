package com.sirius.cybird.ui.activitys.film

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentHomeBinding
import com.sirius.cybird.ui.activitys.home.Test2Fragment
import com.sirius.cybird.ui.base.BaseFragment
import java.util.ArrayList

/**
 *
 *Create By Botasky 03/02/2018
 **/
class FilmFragment :BaseFragment() {
    lateinit var mTestBinding: FragmentHomeBinding
    lateinit var mViewPager: ViewPager
    lateinit var mTabBar: TabLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTestBinding = getBaseViewBinding()
        findviews()
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_home
    }

    fun findviews(){
        mViewPager = mTestBinding.root.findViewById(R.id.id_view_pager)
        val adapter = Adapter(childFragmentManager)
        adapter.addFragment(Test2Fragment(), "Recent")
        adapter.addFragment(Test2Fragment(), "Rank")
        adapter.addFragment(Test2Fragment(), "Classis")
        mViewPager.adapter = adapter
        mTabBar = mTestBinding.root.findViewById(R.id.id_tab_bar)
        mTabBar.setupWithViewPager(mViewPager)
    }

    internal class Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val mFragments = ArrayList<Fragment>()
        private val mFragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            mFragments.add(fragment)
            mFragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return mFragments[position]
        }

        override fun getCount(): Int {
            return mFragments.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitles[position]
        }
    }
}