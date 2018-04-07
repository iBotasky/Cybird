package com.sirius.cybird.ui.base

import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R

abstract class BaseRecyclerFragment : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mMultiStateView: MultiStateView
    lateinit var mMultiStateErrorRetry: View

    override fun setupViews() {
        super.setupViews()
        mMultiStateView = mBinding.root.findViewById(R.id.id_multi_state_view)
        mRecyclerView = mMultiStateView.findViewById(R.id.id_recycler_view)
        mSwipeRefresh = mMultiStateView.findViewById(R.id.id_swipe_refresh)
        mMultiStateView.setViewForState(getMultiStateViewEmpty(), MultiStateView.VIEW_STATE_EMPTY)
        mMultiStateView.setViewForState(getMultiStateViewError(), MultiStateView.VIEW_STATE_ERROR)
        mMultiStateView.setViewForState(getMultiStateViewLoading(), MultiStateView.VIEW_STATE_LOADING)

        mMultiStateErrorRetry = mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)!!.findViewById(R.id.id_multi_state_error_retry)
        mMultiStateErrorRetry.setOnClickListener {
            if (mMultiStateView.viewState != MultiStateView.VIEW_STATE_LOADING) {
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
            }
            loadData()
        }

        mSwipeRefresh.setColorSchemeColors(*getSwipeRefreshColorSchemeRes())

        mRecyclerView.layoutManager = getRecyclerManager()
    }

    open fun getRecyclerManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }

    open fun getSwipeRefreshColorSchemeRes(): IntArray {
        val colorArray = IntArray(4)
        colorArray.set(0, activity!!.resources.getColor(R.color.color_movie))
        colorArray.set(1, activity!!.resources.getColor(R.color.color_daily))
        colorArray.set(2, activity!!.resources.getColor(R.color.color_girl))
        colorArray.set(3, activity!!.resources.getColor(R.color.color_mine))
        return colorArray
    }

    override fun onRefresh() {
        loadData()
    }

    @LayoutRes
    open fun getMultiStateViewEmpty(): Int {
        return R.layout.state_empty_view
    }

    @LayoutRes
    open fun getMultiStateViewError(): Int {
        return R.layout.state_error_view
    }

    @LayoutRes
    open fun getMultiStateViewLoading(): Int {
        return R.layout.state_loading_view
    }

    /**
     * 传入重试方法,自定义重试逻辑
     */
    open fun setOnRetry(retry: () -> Unit) {
        mMultiStateErrorRetry.setOnClickListener { retry() }
    }

}