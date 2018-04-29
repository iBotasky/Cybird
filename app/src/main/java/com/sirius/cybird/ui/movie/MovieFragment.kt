package com.sirius.cybird.ui.movie

import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieBinding
import com.sirius.cybird.module.TabItemData
import com.sirius.cybird.ui.base.BaseTabFragment
import com.sirius.cybird.ui.movie.hot.MovieHotFragment
import com.sirius.cybird.ui.movie.soon.MovieSoonFragment
import com.sirius.cybird.ui.movie.top.MovieTopFragment

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
                TabItemData(R.string.movie_hot, MovieHotFragment::class.java),
                TabItemData(R.string.movie_coming_soon, MovieSoonFragment::class.java),
                TabItemData(R.string.movie_classics, MovieTopFragment::class.java)
        )
    }

    override fun initializeInjector() {

    }
}
