package com.sirius.cybird.ui.movie.hot

import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieHotBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import javax.inject.Inject

class MovieHotFragment : BaseRecyclerFragment<Film, MovieHotAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: MovieHotPresenter

    lateinit var mMovieHotBinding: FragmentMovieHotBinding


    override fun setupViews() {
        super.setupViews()
        mMovieHotBinding = getBaseViewBinding()
        mRecyclerView.addItemDecoration(getVerticalSpaceDecoration())
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_hot
    }

    override fun loadData() {
        mPresenter.getInTheaters()
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { films -> showResults(films) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }
                )
    }

    override fun getAdapter(): BaseQuickAdapter<Film, MovieHotAdapter.ViewHolder> {
        return MovieHotAdapter()
    }

    override fun isEnableLoadMore(): Boolean {
        return true
    }

    private fun showResults(films: List<Film>) {
        if (films.isNotEmpty()) {
            mAdapter.setNewData(films)
//            mRecyclerView.adapter = mAdapter
            mAdapter.loadMoreComplete()
            mAdapter.loadMoreEnd()
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun doLoadMore() {
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}