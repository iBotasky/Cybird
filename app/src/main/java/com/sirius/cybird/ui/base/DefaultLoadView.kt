package com.sirius.cybird.ui.base

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.sirius.cybird.R

/**
 * Created By Botasky 25/04/2018
 */
class DefaultLoadView: LoadMoreView() {
    override fun getLayoutId(): Int {
        return R.layout.default_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}