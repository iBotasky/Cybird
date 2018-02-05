package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment

open abstract class BaseFragment: RxFragment() {
    lateinit var mBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayouResource(), container, false)
        return mBinding.root
    }

    fun <V : ViewDataBinding> getBaseViewBinding(): V{
        return mBinding as V
    }

    abstract fun getLayouResource():Int
}