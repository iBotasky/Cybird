package com.sirius.cybird.ui.daily

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.R
import com.sirius.cybird.net.response.TopStory
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil
import kotlinx.android.synthetic.main.item_daily_top_banner.view.*
import org.jetbrains.annotations.NotNull


/**
 *Created by Botasky on 2018/4/30
 */
class UltraPagerAdapter(@NotNull storyies: List<TopStory>) : PagerAdapter() {
    val mStories: List<TopStory>

    init {
        mStories = storyies
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = mStories[position]
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_daily_top_banner, null)
        GlideUtil.loadImage(context = container.context, imageview = view.findViewById(R.id.iv_img), url = item.image)
        view.tv_title.text = item.title
        view.setOnClickListener({ Navigation.startDailyDetail(context = container.context, id = item.id) })
        container.addView(view)
        return view
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