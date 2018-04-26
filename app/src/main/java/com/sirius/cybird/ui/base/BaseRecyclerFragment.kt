package com.sirius.cybird.ui.base

import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.utils.divider.HorizontalSpaceDecoration
import com.sirius.cybird.utils.divider.VerticalSpaceDecoration

abstract class BaseRecyclerFragment<K, H : BaseViewHolder> : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mMultiStateView: MultiStateView
    lateinit var mMultiStateErrorRetry: View
    lateinit var mAdapter: BaseQuickAdapter<K, H>


    var mPage = 1
    var start = 0

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
        mSwipeRefresh.setOnRefreshListener {
            mPage = 1
            start = 0
            loadData()
        }
        mRecyclerView.layoutManager = getRecyclerManager()

        mAdapter = getAdapter()
        mRecyclerView.adapter = mAdapter
        mAdapter.setEnableLoadMore(isEnableLoadMore())
        mAdapter.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
            override fun onLoadMoreRequested() {
                doLoadMore()
            }
        }, mRecyclerView)
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

    abstract fun getAdapter(): BaseQuickAdapter<K, H>

    open fun doLoadMore() {

    }

    override fun onRefresh() {
        loadData()
    }

    fun refreshEnd() {
        mSwipeRefresh?.isRefreshing = false
    }

    open fun isEnableLoadMore(): Boolean {
        return true
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


    fun getRecyclerViewHeight(): Int {
        return mRecyclerView.height
    }


    fun getHorizontalSpaceDecoration(): RecyclerView.ItemDecoration {
        return HorizontalSpaceDecoration(R.dimen.divider_left_margin, R.dimen.divider_right_margin, R.dimen.divider_item_horizontal)
    }

    fun getHorizontalSpaceDecoration(@DimenRes startSpace: Int, @DimenRes endSpace: Int, @DimenRes itemSpace: Int): RecyclerView.ItemDecoration {
        return HorizontalSpaceDecoration(startSpace, endSpace, itemSpace)
    }

    fun getVerticalSpaceDecoration(): RecyclerView.ItemDecoration {
        return VerticalSpaceDecoration(R.dimen.divider_left_margin, R.dimen.divider_right_margin, R.dimen.divider_item_vertical)
    }

    fun getVerticalSpaceDecoration(@DimenRes startSpace: Int, @DimenRes endSpace: Int, @DimenRes itemSpace: Int): RecyclerView.ItemDecoration {
        return VerticalSpaceDecoration(startSpace, endSpace, itemSpace)
    }

    /**
     * 传入重试方法,自定义重试逻辑
     */
    open fun setOnRetry(retry: () -> Unit) {
        mMultiStateErrorRetry.setOnClickListener { retry() }
    }

}