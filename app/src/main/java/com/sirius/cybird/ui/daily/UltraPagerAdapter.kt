package com.sirius.cybird.ui.daily

import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemDailyTopBannerBinding
import com.sirius.cybird.net.response.TopStory
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil
import org.jetbrains.annotations.NotNull


/**
 *Created by Botasky on 2018/4/30
 */
class UltraPagerAdapter(@NotNull storyies: List<TopStory>) : PagerAdapter() {
    private val mStories: List<TopStory> = storyies

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mBinding: ItemDailyTopBannerBinding = DataBindingUtil.inflate(LayoutInflater.from(container.context), R.layout.item_daily_top_banner, container, false)
        val item = mStories[position]
        mBinding.root.setOnClickListener { Navigation.startDailyDetail(container.context, item.id, item.image) }
        GlideUtil.loadImageCenterCrop(container.context, mBinding.ivImg, item.image)
        mBinding.setVariable(BR.title, item.title)
        mBinding.executePendingBindings()
        container.addView(mBinding.root)
        return mBinding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return mStories.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}