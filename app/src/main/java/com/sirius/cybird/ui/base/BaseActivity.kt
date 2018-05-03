package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.flyco.systembar.SystemBarHelper
import com.kennyc.view.MultiStateView
import com.sirius.cybird.CybirdApp
import com.sirius.cybird.R
import com.sirius.cybird.di.HasComponent
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.di.component.DaggerActivityComponent
import com.sirius.cybird.di.module.ActivityModule
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Description：
 * Created by Botasky on 2017/12/26.
 */
open abstract class BaseActivity : RxAppCompatActivity(), HasComponent<ActivityComponent> {

    lateinit var mBinding: ViewDataBinding
    lateinit var mActivityComponent: ActivityComponent

    var mToolbar: Toolbar? = null
    var mMultiStateView: MultiStateView? = null
    var mMultiStateErrorRetry: View? = null
    var mCollapsingToolbarLayout: CollapsingToolbarLayout? = null

    var mIsDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent()
        initializeInjector()
        mBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        setupToolBar()
        setupStatusBar()
        setupMultiStateView()
        setupViews()
    }


    fun setupComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .repositoryComponent(CybirdApp.getRepostitoryComponent())
                .activityModule(getActivityModule())
                .build()
    }

    abstract fun initializeInjector()

    /**
     * Get an Activity module for dependency injection.
     *
     * @return [ActivityModule]
     */
    protected fun getActivityModule(): ActivityModule {
        return ActivityModule(this)
    }

    @CallSuper
    open fun setupViews() {
    }

    fun <V : ViewDataBinding> getBaseViewBinding(): V {
        return mBinding as V
    }

    abstract fun getLayoutResource(): Int


    fun isDark(): Boolean {
        return mIsDark
    }

    open fun isImmersiveStatusBar(): Boolean {
        return true
    }

    fun getImmersiveStatusBarAlpha(): Float {
        if (isDark()) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                if (SystemBarHelper.isMIUI6Later() || SystemBarHelper.isFlyme4Later() || Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                } else {
                    return 0.3f
                }
            }
        }
        return 0f
    }

    fun setupToolBar() {
        mToolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(mToolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnable())
    }


    fun setupMultiStateView() {
        mMultiStateView = findViewById(R.id.id_multi_state_view)
        mMultiStateView?.setViewForState(getMultiStateViewEmpty(), MultiStateView.VIEW_STATE_EMPTY)
        mMultiStateView?.setViewForState(getMultiStateViewError(), MultiStateView.VIEW_STATE_ERROR)
        mMultiStateView?.setViewForState(getMultiStateViewLoading(), MultiStateView.VIEW_STATE_LOADING)

        mMultiStateErrorRetry = mMultiStateView?.getView(MultiStateView.VIEW_STATE_ERROR)?.findViewById(R.id.id_multi_state_error_retry)
        mMultiStateErrorRetry?.setOnClickListener {
            if (mMultiStateView?.viewState != MultiStateView.VIEW_STATE_LOADING) {
                mMultiStateView?.viewState = MultiStateView.VIEW_STATE_LOADING
            }
            loadData()
        }
    }


    open fun loadData(){

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    open fun setToolbarTitle(@StringRes res: Int) {
        var titleView: TextView? = findViewById(R.id.tool_bar_title)
        titleView?.setText(this.getString(res))
    }


    open fun isDisplayHomeAsUpEnable(): Boolean {
        return false
    }


    protected fun setupStatusBar() {
        if (isDark()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                SystemBarHelper.setStatusBarDarkMode(this)
            }
        }

        if (!isImmersiveStatusBar()) {
            return
        }

        SystemBarHelper.immersiveStatusBar(this, getImmersiveStatusBarAlpha())

        if (mToolbar != null) {
            SystemBarHelper.setHeightAndPadding(this, mToolbar)
        }
    }

    override fun getComponent(): ActivityComponent {
        return mActivityComponent
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

    /**
     * 传入重试方法,自定义重试逻辑
     */
    fun setOnRetry(retry: () -> Unit) {
        mMultiStateErrorRetry?.setOnClickListener { retry() }
    }
}