package com.sirius.cybird.ui.movie.detail

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemDailyBannerBinding
import com.sirius.cybird.databinding.ItemDailyStoryBinding

/**
 * Created By Botasky 2018/5/4
 */
class MovieDetailAdapter(val list:List<MultiItemEntity>): BaseMultiItemQuickAdapter<MultiItemEntity, MovieDetailAdapter.ViewHolder>(list) {
    override fun convert(helper: ViewHolder?, item: MultiItemEntity?) {

    }


    class ViewHolder(view: View) : BaseViewHolder(view) {
        val bannerBinding: ItemDailyBannerBinding
            get() = itemView.getTag(R.id.id_tag_daily_banner) as ItemDailyBannerBinding

        val storyBinding: ItemDailyStoryBinding
            get() = itemView.getTag(R.id.id_tag_daily_story) as ItemDailyStoryBinding
    }
}