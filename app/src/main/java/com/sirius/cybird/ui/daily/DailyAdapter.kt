package com.sirius.cybird.ui.daily

import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemDailyBannerBinding
import com.sirius.cybird.databinding.ItemDailyStoryBinding

/**
 *Created by Botasky on 2018/4/29
 */
class DailyAdapter(val list: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, DailyAdapter.ViewHolder>(list) {
    init {
        addItemType(BANNER, R.layout.item_daily_banner)
        addItemType(STORY, R.layout.item_daily_story)
    }

    companion object {
        val BANNER = 1
        val STORY = 2
    }

    override fun convert(helper: ViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            BANNER -> {

            }

            STORY -> {

            }
        }
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        //有loadmore的时候需要return super.getItemView(layoutResId, parent)
        when (layoutResId) {
            R.layout.item_daily_banner -> {
                val binding: ItemDailyBannerBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.id_tag_daily_banner, binding)
                return view
            }
            R.layout.item_daily_story -> {
                val binding: ItemDailyStoryBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.id_tag_daily_story, binding)
                return view
            }
            else -> {
                return super.getItemView(layoutResId, parent)
            }
        }

//        val binding:ViewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
//                ?: return super.getItemView(layoutResId, parent)
//        val view = binding.root
//        view.setTag(R.id.id_tag_movie, binding)
//        return view
//        val binding:ViewDataBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)

    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val bannerBinding: ItemDailyBannerBinding
            get() = itemView.getTag(R.id.id_tag_daily_banner) as ItemDailyBannerBinding

        val storyBinding: ItemDailyStoryBinding
            get() = itemView.getTag(R.id.id_tag_daily_story) as ItemDailyStoryBinding
    }
}