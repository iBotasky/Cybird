package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.R
import com.trello.rxlifecycle2.components.support.RxFragment

open abstract class BaseFragment: RxFragment() {
    lateinit var mBinding: ViewDataBinding
    var mToolBar: Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayouResource(), container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    @CallSuper
    open fun setupViews(){
        mToolBar = mBinding.root.findViewById(R.id.id_toolbar)
    }

    open fun setToolBarTitle(@StringRes res: Int){
        mToolBar?.setTitle(res)
    }

    open fun setToolBarLogo(@DrawableRes res: Int){
        mToolBar?.setLogo(res)
    }

    fun <V : ViewDataBinding> getBaseViewBinding(): V{
        return mBinding as V
    }

    @LayoutRes
    abstract fun getLayouResource():Int
}