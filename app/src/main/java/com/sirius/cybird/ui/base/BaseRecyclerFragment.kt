package com.sirius.cybird.ui.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.sirius.cybird.R

abstract class BaseRecyclerFragment:BaseFragment(), SwipeRefreshLayout.OnRefreshListener{
    lateinit var mRecyclerView: RecyclerView
    lateinit var mSwipeRefresh: SwipeRefreshLayout
    

    override fun setupViews() {
        super.setupViews()
        mRecyclerView = mBinding.root.findViewById(R.id.id_recycler_view)
        mSwipeRefresh = mBinding.root.findViewById(R.id.id_swipe_refresh)
    }

}