package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.di.HasComponent
import com.trello.rxlifecycle2.components.support.RxFragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseFragment : RxFragment(), AnkoLogger {
    lateinit var mBinding: ViewDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, getLayouResource(), container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        initializeInjector()
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    open fun setupViews() {
    }


    fun <V : ViewDataBinding> getBaseViewBinding(): V {
        return mBinding as V
    }

    @LayoutRes
    abstract fun getLayouResource(): Int

    abstract fun initializeInjector()

    /**
     * Gets a component for dependency injection by its type.
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).getComponent())
    }
}