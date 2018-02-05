package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.flyco.systembar.SystemBarHelper
import com.sirius.cybird.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.include_tool_bar.view.*

/**
 * Description：
 * Created by Botasky on 2017/12/26.
 */
open abstract class BaseActivity : RxAppCompatActivity() {

    lateinit var mBinding: ViewDataBinding
    var mToolbar: Toolbar? = null

    var mIsDark = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        mToolbar = findViewById(R.id.id_toolbar)
        setupStatusBar()
        setupToolBar()
    }

    fun <V : ViewDataBinding> getBaseViewBinding(): V {
        return mBinding as V
    }

    abstract fun getLayoutResource(): Int


    fun isDark(): Boolean {
        return mIsDark
    }

    open fun isImmersiveStatusBar(): Boolean {
        return false
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

    fun setupToolBar(){
        if (mToolbar != null){
            setSupportActionBar(mToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnable())
            supportActionBar?.setTitle("")
        }
    }


    open fun isDisplayHomeAsUpEnable(): Boolean{
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
}