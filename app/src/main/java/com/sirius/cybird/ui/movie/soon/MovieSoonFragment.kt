package com.sirius.cybird.ui.movie.soon

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieSoonBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import com.sirius.cybird.ui.movie.hot.MovieHotAdapter
import javax.inject.Inject

/**
 * Created By Botasky 28/04/2018
 */
class MovieSoonFragment : BaseRecyclerFragment<Film, MovieHotAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: MovieSoonPresenter
    lateinit var mMovieSoonBinding: FragmentMovieSoonBinding

    override fun setupViews() {
        super.setupViews()
        mMovieSoonBinding = getBaseViewBinding()
        mRecyclerView.addItemDecoration(getVerticalSpaceDecoration())
    }

    override fun getAdapter(): BaseQuickAdapter<Film, MovieHotAdapter.ViewHolder> {
        return MovieHotAdapter()
    }

    override fun loadData() {
        mPresenter.getComingSoon(mStart)
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { filmData -> showResults(filmData.films) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }

                )
    }


    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_soon
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}