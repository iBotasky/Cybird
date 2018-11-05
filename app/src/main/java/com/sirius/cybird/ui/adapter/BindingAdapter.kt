package com.sirius.cybird.ui.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.sirius.cybird.utils.GlideUtil

/**
 *
 *Create by Botasky 2018/11/5
 */
@BindingAdapter("app:loadImage")
fun bindingImageView(view: ImageView, url: String){
    GlideUtil.loadImageNotCrop(view.context, view, url)
}