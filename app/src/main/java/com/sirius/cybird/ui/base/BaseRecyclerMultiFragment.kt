package com.sirius.cybird.ui.base

import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.kennyc.view.MultiStateView
import com.melnykov.fab.FloatingActionButton
import com.melnykov.fab.ScrollDirectionListener
import com.sirius.cybird.R
import com.sirius.cybird.utils.divider.HorizontalSpaceDecoration
import com.sirius.cybird.utils.divider.VerticalSpaceDecoration

/**
 *Created by Botasky on 2018/4/29
 */
//TODO: 不懂得如何把所有的Adapter封装成一个基类，目前只能分开封装
abstract class BaseRecyclerMultiFragment<H : BaseViewHolder> : BaseLazyFragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mMultiStateView: MultiStateView
    lateinit var mMultiStateErrorRetry: View
    lateinit var mAdapter: BaseMultiItemQuickAdapter<MultiItemEntity, H>
    var mFloatingButton: FloatingActionButton? = null

    var mPage = 1
    var mStart = 0

    override fun setupViews() {
        super.setupViews()
        mMultiStateView = mBinding.root.findViewById(R.id.id_multi_state_view)
        mRecyclerView = mMultiStateView.findViewById(R.id.id_recycler_view)
        mSwipeRefresh = mMultiStateView.findViewById(R.id.id_swipe_refresh)
        mSwipeRefresh.isEnabled = isEnableSwipeLayout()

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
            mStart = 0
            loadData()
        }
        mRecyclerView.layoutManager = getRecyclerManager()

        mAdapter = getAdapter()
        mRecyclerView.adapter = mAdapter

        if (isEnableLoadMore()) {
            mAdapter.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener {
                override fun onLoadMoreRequested() {
                    doLoadMore()
                }
            }, mRecyclerView)
        }else{
            mAdapter.setEnableLoadMore(false)
        }
        mFloatingButton = mBinding.root.findViewById(R.id.id_float_button)
        mFloatingButton?.hide()
        mFloatingButton?.attachToRecyclerView(mRecyclerView, object : ScrollDirectionListener {
            override fun onScrollUp() {
                mFloatingButton?.hide()
            }

            override fun onScrollDown() {
                mFloatingButton?.show()
            }

        }, object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition > 10) {
                        mFloatingButton?.show()
                    } else {
                        mFloatingButton?.hide()
                    }
                }
                if (layoutManager is GridLayoutManager) {
                    val firstvisible = layoutManager.findFirstVisibleItemPosition()
                    if (firstvisible > 20) {
                        mFloatingButton?.show()
                    } else {
                        mFloatingButton?.hide()
                    }
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        mFloatingButton?.setOnClickListener(
                {
                    v -> mRecyclerView.scrollToPosition(0)
                    mFloatingButton?.hide()
                }
        )
    }

    open fun getRecyclerManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    open fun getSwipeRefreshColorSchemeRes(): IntArray {
        val colorArray = IntArray(4)
        colorArray.set(0, activity!!.resources.getColor(R.color.color_movie))
        colorArray.set(1, activity!!.resources.getColor(R.color.color_daily))
        colorArray.set(2, activity!!.resources.getColor(R.color.color_girl))
        colorArray.set(3, activity!!.resources.getColor(R.color.color_mine))
        return colorArray
    }

    abstract fun getAdapter(): BaseMultiItemQuickAdapter<MultiItemEntity, H>

    open fun doLoadMore() {
        loadData()
    }

    fun refreshEnd() {
        mSwipeRefresh?.isRefreshing = false
    }

    open fun isEnableLoadMore(): Boolean {
        return true
    }

    open fun isEnableSwipeLayout():Boolean{
        return  true
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