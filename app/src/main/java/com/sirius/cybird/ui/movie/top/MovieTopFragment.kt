package com.sirius.cybird.ui.movie.top

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieTopBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.net.response.FilmsData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import javax.inject.Inject

class MovieTopFragment : BaseRecyclerFragment<Film, MovieTopAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: MovieTopPresenter

    lateinit var mMovieTopBinding: FragmentMovieTopBinding


    override fun setupViews() {
        super.setupViews()
        mMovieTopBinding = getBaseViewBinding()
        mRecyclerView.addItemDecoration(getVerticalSpaceDecoration())
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun getAdapter(): BaseQuickAdapter<Film, MovieTopAdapter.ViewHolder> {
        return MovieTopAdapter()
    }

    override fun loadData() {
        mPresenter.getTopFilms(mStart)
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { filmData -> showResult(filmData) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }

                )
    }

    private fun showResult(filmsData: FilmsData) {
        if (mPage == 1 || mStart == 0){
            mAdapter.setNewData(filmsData.films)
        } else if (mAdapter.data.size < filmsData.total) {
            mAdapter.addData(filmsData.films)
        }
        mAdapter.loadMoreComplete()
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        mStart += filmsData.films.size
        mPage += 1
        if (mAdapter.data.size == 0){
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }else if (mStart >= filmsData.total){
            mAdapter.loadMoreEnd()
        }
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_top
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}