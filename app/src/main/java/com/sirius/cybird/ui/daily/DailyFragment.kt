package com.sirius.cybird.ui.daily

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerMultiFragment
import javax.inject.Inject

class DailyFragment : BaseRecyclerMultiFragment<DailyAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: DailyPresenter


    override fun setupViews() {
        super.setupViews()
        mRecyclerView.addItemDecoration(getVerticalSpaceDecoration(R.dimen.dimen_0, R.dimen.dimen_0,R.dimen.dimen_5))
        mRecyclerView.isVerticalScrollBarEnabled = false
        mFloatingButton?.visibility = View.GONE
    }

    override fun getAdapter(): BaseMultiItemQuickAdapter<MultiItemEntity, DailyAdapter.ViewHolder> {
        return DailyAdapter(arrayListOf())
    }

    override fun loadData() {
        mPresenter.getDailyStories()
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { multiItems -> showResult(multiItems) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }
                )
    }

    private fun showResult(multiList: List<MultiItemEntity>) {
        if (multiList.isNotEmpty()) {
            mAdapter.setNewData(multiList)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_daily
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }

    override fun doLoadMore() {
        mAdapter.loadMoreEnd()
    }

}