package com.sirius.cybird.ui.movie.detail

import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMovieDetailBinding
import com.sirius.cybird.net.response.FilmDetailData
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.GlideUtil
import javax.inject.Inject

/**
 * Created By Botasky 02/05/2018
 */
class MovieDetailActivity : BaseActivity() {

    @Inject
    lateinit var mPresenter: MovieDetailPresenter
    lateinit var mDetailBinding: ActivityMovieDetailBinding

    lateinit var mId: String

    init {

    }

    override fun setupViews() {
        super.setupViews()
        mId = intent.getStringExtra(Navigation.EXTRA_ID)
        mDetailBinding = getBaseViewBinding()
        GlideUtil.loadImage(this, mDetailBinding.ivFilmImg, intent.getStringExtra(Navigation.EXTRA_IMG))
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.id_continer, MovieDetailContentFragment.newInstance(mId))
        fragmentTransaction.commitAllowingStateLoss()
    }

    override fun isDisplayHomeAsUpEnable(): Boolean {
        return true
    }

    override fun initializeInjector() {
        component.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_movie_detail
    }
}