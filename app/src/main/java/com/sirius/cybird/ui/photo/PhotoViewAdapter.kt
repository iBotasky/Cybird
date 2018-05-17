package com.sirius.cybird.ui.photo

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.chrisbanes.photoview.PhotoView
import com.sirius.cybird.utils.GlideUtil
import org.jetbrains.annotations.NotNull

/**
 * Created By Botasky 2018/5/17
 */
class PhotoViewAdapter(@NotNull urls: List<String>, @NotNull context: Context) : PagerAdapter() {
    val mUrls: List<String>
    val mContext: Context

    init {
        mUrls = urls
        mContext = context
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val photoView = PhotoView(mContext)
        photoView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        photoView.scaleType = ImageView.ScaleType.FIT_XY
        GlideUtil.loadImage(mContext, photoView, mUrls[position])
        container.addView(photoView)
        return photoView
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return mUrls.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}