package com.sirius.cybird.utils

import android.content.Context
import android.widget.ImageView
import com.sirius.cybird.R
import java.io.File
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget



object GlideUtil {

    fun loadImage(context: Context, imageview: ImageView, url: String){
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.img_holder)
                .error(R.drawable.img_err_holder)
                .centerCrop()
                .into(imageview)
    }

    fun loadImageNotCrop(context: Context, imageview: ImageView, url: String){
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.img_holder)
                .error(R.drawable.img_err_holder)
                .into(imageview)
    }


    /**
     * 下载Url的图片到File
     * submit()方法其实就是对应的Glide 3中的downloadOnly()方法，和preload()方法类似，
     * submit()方法也是可以替换into()方法的，不过submit()方法的用法明显要比preload()方法复杂不少。
     * 这个方法只会下载图片，而不会对图片进行加载。当图片下载完成之后，我们可以得到图片的存储路径，以便后续进行操作。
     */
    fun getFile(context: Context, url: String): File {
        val future = GlideApp
                .with(context)
                .asFile()
                .load(url)
                .submit()
        return future.get()
    }
}