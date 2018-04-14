package com.sirius.cybird.ui.movie.hot

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieHotBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import com.sirius.cybird.utils.divider.HorizontalDividerItemDecoration
import com.sirius.cybird.utils.divider.SpaceDecoration
import com.sirius.cybird.utils.divider.VerticalDividerItemDecoration
import javax.inject.Inject

class MovieHotFragment : BaseRecyclerFragment() {

    @Inject
    lateinit var mPresenter: MovieHotPresenter

    override fun onRefresh() {
        loadData()
    }

    lateinit var mMovieHotBinding: FragmentMovieHotBinding
    override fun setupViews() {
        super.setupViews()
        mMovieHotBinding = getBaseViewBinding()
    }

    override fun getRecyclerManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
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
                        { }
                )
    }

    private fun showResults(films: List<Film>) {
        if (films.isNotEmpty()) {
            mRecyclerView.adapter = MovieHotAdapter(films)

            mRecyclerView.addItemDecoration(SpaceDecoration(ConvertUtils.dp2px(12.0f)))
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }


    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}