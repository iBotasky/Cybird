package com.sirius.cybird.ui.photo

import com.sirius.cybird.R
import com.sirius.cybird.ui.base.BaseActivity

/**
 *Created by Botasky on 2018/5/12
 */
class PhotoViewActivity :BaseActivity(){


    override fun setupViews() {
        super.setupViews()
    }

    override fun isDisplayHomeAsUpEnable(): Boolean {
        return true
    }

    override fun initializeInjector() {
        component.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_photo_view
    }
}