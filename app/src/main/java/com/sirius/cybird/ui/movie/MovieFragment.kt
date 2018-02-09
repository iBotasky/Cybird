package com.sirius.cybird.ui.movie

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieBinding
import com.sirius.cybird.module.TabItemData
import com.sirius.cybird.ui.home.Test2Fragment
import com.sirius.cybird.ui.base.BaseTabFragment
import java.util.ArrayList

/**
 *
 *Create By Botasky 03/02/2018
 **/
class MovieFragment :BaseTabFragment() {
    lateinit var mMovieBinding: FragmentMovieBinding

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie
    }

    override fun setupViews() {
        super.setupViews()
        mMovieBinding = getBaseViewBinding()
    }


    override fun getTabItems(): List<TabItemData> {
        val tabItems = ArrayList<TabItemData>()
        tabItems.add(TabItemData(R.string.movie_hot, Test2Fragment::class.java))
        tabItems.add(TabItemData(R.string.movie_coming_soon, Test2Fragment::class.java))
        tabItems.add(TabItemData(R.string.movie_classics, Test2Fragment::class.java))
        return tabItems
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