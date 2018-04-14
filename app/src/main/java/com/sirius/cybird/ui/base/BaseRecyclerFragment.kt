package com.sirius.cybird.ui.base

import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.utils.divider.HorizontalDividerItemDecoration
import com.sirius.cybird.utils.divider.VerticalDividerItemDecoration

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


    fun getRecyclerViewHeight(): Int {
        return mRecyclerView.height
    }

//    fun hasLastDivider(): Boolean {
//        return true
//    }
//
//    fun getHorizontalItemDecoration(): RecyclerView.ItemDecoration {
//        return getHorizontalItemDecorationResId(R.dimen.divider_left_margin, R.dimen.divider_right_margin)
//    }
//
//    fun getHorizontalItemDecoration(leftMargin: Int, rightMargin: Int): RecyclerView.ItemDecoration {
//        return getHorizontalItemDecoration(leftMargin, rightMargin, resources.getDimensionPixelSize(R.dimen.divider_size))
//    }
//
//    fun getHorizontalItemDecoration(leftMargin: Int, rightMargin: Int, size: Int): RecyclerView.ItemDecoration {
//        val builder = HorizontalDividerItemDecoration.Builder(context)
//                .colorResId(R.color.list_divider)
//                .size(size)
//                .margin(leftMargin, rightMargin)
//        if (hasLastDivider()) {
//            builder.showLastDivider()
//        }
//        return builder.build()
//    }
//
//    fun getHorizontalItemDecorationResId(@DimenRes leftMarginId: Int, @DimenRes rightMarginId: Int): RecyclerView.ItemDecoration {
//        return getHorizontalItemDecorationResId(leftMarginId, rightMarginId, R.dimen.divider_size)
//    }
//
//    fun getHorizontalItemDecorationResId(@DimenRes leftMarginId: Int, @DimenRes rightMarginId: Int, @DimenRes sizeResId: Int): RecyclerView.ItemDecoration {
//        val builder = HorizontalDividerItemDecoration.Builder(context)
//                .colorResId(R.color.list_divider)
//                .sizeResId(sizeResId)
//                .marginResId(leftMarginId, rightMarginId)
//        if (hasLastDivider()) {
//            builder.showLastDivider()
//        }
//        return builder.build()
//    }
//
//    fun getVerticalItemDecoration(): RecyclerView.ItemDecoration {
//        return getVerticalItemDecorationResId(R.dimen.divider_top_margin, R.dimen.divider_bottom_margin)
//    }
//
//    fun getVerticalItemDecoration(topMargin: Int, bottomMargin: Int): RecyclerView.ItemDecoration {
//        val builder = VerticalDividerItemDecoration.Builder(context)
//                .colorResId(R.color.list_divider)
//                .sizeResId(R.dimen.divider_size)
//                .margin(topMargin, bottomMargin)
//        if (hasLastDivider()) {
//            builder.showLastDivider()
//        }
//        return builder.build()
//    }
//
//    fun getVerticalItemDecorationResId(@DimenRes topMarginId: Int, @DimenRes bottomMarginId: Int): RecyclerView.ItemDecoration {
//        val builder = VerticalDividerItemDecoration.Builder(context)
//                .colorResId(R.color.list_divider)
//                .sizeResId(R.dimen.divider_size)
//                .marginResId(topMarginId, bottomMarginId)
//        if (hasLastDivider()) {
//            builder.showLastDivider()
//        }
//        return builder.build()
//    }

    /**
     * 传入重试方法,自定义重试逻辑
     */
    open fun setOnRetry(retry: () -> Unit) {
        mMultiStateErrorRetry.setOnClickListener { retry() }
    }

}