package com.sirius.cybird.ui.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sirius.cybird.R

abstract class BaseRecyclerFragment : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout


    override fun setupViews() {
        super.setupViews()
        mRecyclerView = mBinding.root.findViewById(R.id.id_recycler_view)
        mSwipeRefresh = mBinding.root.findViewById(R.id.id_swipe_refresh)
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

}