package com.sirius.cybird.utils

import android.content.Context
import android.widget.ImageView
import com.sirius.cybird.R

object GlideUtil {

    fun loadImage(context: Context, imageview: ImageView, url: String){
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.img_holder)
                .error(R.drawable.img_err_holder)
                .centerCrop()
                .into(imageview)
    }
}