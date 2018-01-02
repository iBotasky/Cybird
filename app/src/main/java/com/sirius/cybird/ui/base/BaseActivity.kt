package com.sirius.cybird.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.sirius.cybird.R
import com.trello.rxlifecycle2.components.RxActivity

/**
 * Description：
 * Created by Botasky on 2017/12/26.
 */
open class BaseActivity<M : ViewDataBinding> : RxActivity() {
    var mBinding: M? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutResource())
    }

    open fun getLayoutResource(): Int {
        return -1
    }
}