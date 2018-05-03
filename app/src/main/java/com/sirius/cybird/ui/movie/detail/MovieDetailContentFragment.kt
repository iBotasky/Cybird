package com.sirius.cybird.ui.movie.detail

import android.os.Bundle
import android.text.TextUtils
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieDetailBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.FilmDetailData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerMultiFragment
import com.sirius.cybird.ui.daily.DailyAdapter
import javax.inject.Inject


/**
 * Created By Botasky 2018/5/3
 */
class MovieDetailContentFragment : BaseRecyclerMultiFragment<DailyAdapter.ViewHolder>() {
    private var mId: String? = null

    @Inject
    lateinit var mPresenter: MovieDetailPresenter

    lateinit var mMovieDetailFragmentBinding: FragmentMovieDetailBinding

    companion object {
        val MOVIE_ID = "id"

        fun newInstance(id: String): MovieDetailContentFragment {
            val bundle = Bundle()
            bundle.putString(MOVIE_ID, id)
            val movieDetailFragment = MovieDetailContentFragment()
            movieDetailFragment.setArguments(bundle)
            return movieDetailFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        mId = bundle?.getString(MOVIE_ID)
    }

    override fun setupViews() {
        super.setupViews()
        mMovieDetailFragmentBinding = getBaseViewBinding()
    }


    override fun getAdapter(): BaseMultiItemQuickAdapter<MultiItemEntity, DailyAdapter.ViewHolder> {
        return DailyAdapter(arrayListOf())
    }

    override fun loadData() {
        if (!TextUtils.isEmpty(mId)) {
            mPresenter.getFilmDetail(mId!!)
                    .compose(bindToLifecycle())
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .subscribe(
                            { filmdetailData -> showData(filmdetailData) },
                            { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                            {}
                    )
        }
    }

    private fun showData(filmDetailData: FilmDetailData) {
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_detail
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }

    override fun isEnableSwipeLayout(): Boolean {
        return false
    }
}