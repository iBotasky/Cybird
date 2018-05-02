package com.sirius.cybird.ui.movie

import com.sirius.cybird.R
import com.sirius.cybird.ui.base.BaseActivity

/**
 * Created By Botasky 02/05/2018
 */
class MovieDetailActivity : BaseActivity() {
    override fun initializeInjector() {
        component.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_movie_detail
    }
}