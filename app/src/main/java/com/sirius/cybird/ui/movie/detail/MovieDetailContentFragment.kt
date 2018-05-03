package com.sirius.cybird.ui.movie.detail

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.ui.base.BaseRecyclerMultiFragment
import com.sirius.cybird.ui.daily.DailyAdapter

/**
 * Created By Botasky 2018/5/3
 */
class MovieDetailContentFragment: BaseRecyclerMultiFragment<DailyAdapter.ViewHolder>() {
    override fun getAdapter(): BaseMultiItemQuickAdapter<MultiItemEntity, DailyAdapter.ViewHolder> {
        return DailyAdapter(arrayListOf())
    }

    override fun loadData() {
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_detail
    }

    override fun initializeInjector() {
    }

    override fun isEnableSwipeLayout(): Boolean {
        return false
    }
}