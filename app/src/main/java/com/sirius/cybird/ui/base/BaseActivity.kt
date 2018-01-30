package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Description：
 * Created by Botasky on 2017/12/26.
 */
open class BaseActivity : RxAppCompatActivity() {
    lateinit var mBinding: ViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutResource())
    }

    fun <V : ViewDataBinding> getBaseViewBinding(): V {
        return mBinding as V
    }

    open fun getLayoutResource(): Int {
        return 0
    }
}