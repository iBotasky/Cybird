package com.sirius.cybird.ui.base

import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kennyc.view.MultiStateView
import com.melnykov.fab.FloatingActionButton
import com.melnykov.fab.ScrollDirectionListener
import com.sirius.cybird.R
import com.sirius.cybird.utils.divider.HorizontalSpaceDecoration
import com.sirius.cybird.utils.divider.VerticalSpaceDecoration

enum class LoadState() {
    REFRESH,
    LOAD_MORE
}

//TODO: 这里的doLoadMore为什么直接调用loadData()方法?
abstract class BaseRecyclerFragment<K, H : BaseViewHolder> : BaseLazyFragment() {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout
    lateinit var mMultiStateView: MultiStateView
    lateinit var mMultiStateErrorRetry: View
    lateinit var mAdapter: BaseQuickAdapter<K, H>
    var mFloatingButton: FloatingActionButton? = null


    var mPage = 1
    //主要是豆瓣api需要
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

        setOnErrorRetry { loadData() }

        mSwipeRefresh.setColorSchemeColors(*getSwipeRefreshColorSchemeRes())
        mSwipeRefresh.setOnRefreshListener {
            mPage = 1
            mStart = 0
            loadData()
        }
        mRecyclerView.layoutManager = getLayoutManager()

        mAdapter = getAdapter()
        mRecyclerView.adapter = mAdapter
        mAdapter.setOnLoadMoreListener({ doLoadMore() }, mRecyclerView)

        mFloatingButton = mBinding.root.findViewById(R.id.id_float_button)
        mFloatingButton?.hide()
        mFloatingButton?.attachToRecyclerView(mRecyclerView, object : ScrollDirectionListener {
            override fun onScrollUp() {
                mFloatingButton?.show()
            }

            override fun onScrollDown() {
                mFloatingButton?.hide()
            }

        }, object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager) {
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                    if (firstVisibleItemPosition > 5) {
                        mFloatingButton?.show()
                    } else {
                        mFloatingButton?.hide()
                    }
                }
                if (layoutManager is GridLayoutManager) {
                    val firstvisible = layoutManager.findFirstVisibleItemPosition()
                    if (firstvisible > 10) {
                        mFloatingButton?.show()
                    } else {
                        mFloatingButton?.hide()
                    }
                }
            }

//            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//            }
        })
        mFloatingButton?.setOnClickListener { v ->
            mRecyclerView.scrollToPosition(0)
            mFloatingButton?.hide()
        }
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    open fun getSwipeRefreshColorSchemeRes(): IntArray {
        val colorArray = IntArray(4)
        colorArray[0] = activity!!.resources.getColor(R.color.color_movie)
        colorArray[1] = activity!!.resources.getColor(R.color.color_daily)
        colorArray[2] = activity!!.resources.getColor(R.color.color_girl)
        colorArray[3] = activity!!.resources.getColor(R.color.color_mine)
        return colorArray
    }

    abstract fun getAdapter(): BaseQuickAdapter<K, H>

    open fun doLoadMore() {
        loadData()
    }

    protected open fun showResults(results: List<K>) {
        if (mPage == 1 || mStart == 0) {
            mAdapter.setNewData(results)
        } else {
            mAdapter.addData(results)
        }
        mAdapter.loadMoreComplete()
        mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        mStart += results.size
        mPage += 1
        if (mAdapter.data.size == 0) {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        } else if (results.isEmpty()) {
            mAdapter.loadMoreEnd()
        }
    }

    fun refreshEnd() {
        mSwipeRefresh?.isRefreshing = false
    }

    open fun isEnableLoadMore(): Boolean {
        return true
    }

    open fun isEnableSwipeLayout(): Boolean {
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
    open fun setOnErrorRetry(retry: () -> Unit) {
        mMultiStateErrorRetry = mMultiStateView.getView(MultiStateView.VIEW_STATE_ERROR)!!.findViewById(R.id.id_multi_state_error_retry)
        mMultiStateErrorRetry.setOnClickListener {
            if (mMultiStateView.viewState != MultiStateView.VIEW_STATE_LOADING) {
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_LOADING
            }
            retry()
        }
    }

}