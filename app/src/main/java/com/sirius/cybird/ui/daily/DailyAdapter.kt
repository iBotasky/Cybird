package com.sirius.cybird.ui.daily

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemDailyBannerBinding
import com.sirius.cybird.databinding.ItemDailyStoryBinding
import com.sirius.cybird.net.response.Story
import com.sirius.cybird.net.response.TopStories
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil
import com.tmall.ultraviewpager.UltraViewPager


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
                val topStories = item as TopStories
                val bannerBinding = helper.bannerBinding
                bannerBinding.idViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL)
                //initialize UltraPagerAdapter，and add child view to UltraViewPager
                val adapter = UltraPagerAdapter(item.topstories)
                bannerBinding.idViewPager.setAdapter(adapter)

                bannerBinding.idViewPager.initIndicator()
                //set style of indicators
                bannerBinding.idViewPager.getIndicator()
                        .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                        .setFocusColor(Color.GRAY)
                        .setNormalColor(Color.WHITE)
                        .setRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f, mContext.getResources().getDisplayMetrics()).toInt())
                bannerBinding.idViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM)
                        .setMargin(0, 0, 0, 15)
                //construct built-in indicator, and add it to  UltraViewPager
                bannerBinding.idViewPager.getIndicator().build()
                //set an infinite loop
                bannerBinding.idViewPager.setInfiniteLoop(true)
                //enable auto-scroll mode
                bannerBinding.idViewPager.setAutoScroll(5000)
            }

            STORY -> {
                val story = item as Story
                val storyBinding = helper.storyBinding
                GlideUtil.loadImageCenterCrop(mContext, storyBinding.ivImg, story.images[0])
                storyBinding.setVariable(BR.title, story.title)
                storyBinding.executePendingBindings()
                helper.itemView.setOnClickListener(
                        ({ v -> Navigation.startDailyDetail(mContext, story.id, story.images[0]) })
                )
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
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val bannerBinding: ItemDailyBannerBinding
            get() = itemView.getTag(R.id.id_tag_daily_banner) as ItemDailyBannerBinding

        val storyBinding: ItemDailyStoryBinding
            get() = itemView.getTag(R.id.id_tag_daily_story) as ItemDailyStoryBinding
    }
}