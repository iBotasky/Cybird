package com.sirius.cybird.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseLazyFragment : BaseFragment() {
    //控件是否初始化完成
    private var isViewCreated = false

    //第一次数据是否加载完成
    private var isFirstLoadDataCompleted = false

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isViewCreated && !isFirstLoadDataCompleted) {
            loadData()
            isFirstLoadDataCompleted = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isViewCreated = true
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /**
     * 对于setVisiableHint早于onCreateView()调用
     * 即第一个Fragment可见的时候去loadata，rootView会是空的，所以要在onActivityCreated()加一层调用
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (userVisibleHint){
            loadData()
            isFirstLoadDataCompleted = true
        }
    }

    abstract fun loadData()
}

