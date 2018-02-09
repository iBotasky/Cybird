package com.sirius.cybird.ui.movie

import android.support.design.widget.TabItem
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieBinding
import com.sirius.cybird.module.TabItemData
import com.sirius.cybird.ui.base.BaseTabFragment
import com.sirius.cybird.ui.home.Test2Fragment
import java.util.*

/**
 *
 *Create By Botasky 03/02/2018
 **/
class MovieFragment : BaseTabFragment() {
    lateinit var mMovieBinding: FragmentMovieBinding

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie
    }

    override fun setupViews() {
        super.setupViews()
        mMovieBinding = getBaseViewBinding()
    }


    override fun getTabItems(): List<TabItemData> {
        return listOf(
                TabItemData(R.string.movie_hot, Test2Fragment::class.java),
                TabItemData(R.string.movie_coming_soon, Test2Fragment::class.java),
                TabItemData(R.string.movie_classics, Test2Fragment::class.java)
        )
    }
}