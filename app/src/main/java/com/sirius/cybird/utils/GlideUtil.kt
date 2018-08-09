package com.sirius.cybird.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.widget.ImageView
import com.sirius.cybird.R
import io.reactivex.Observable
import java.io.File


object GlideUtil {

    fun loadImage(context: Context, imageview: ImageView, url: String) {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.img_holder)
                .error(R.drawable.img_err_holder)
                .centerCrop()
                .into(imageview)
    }

    fun loadImageNotCrop(context: Context, imageview: ImageView, url: String) {
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
    //TODO 后缀问题
    fun saveImage(context: Context, url: String): Observable<String> {
        val regex = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|png|jpeg)".toRegex()
        if (!url.matches(regex)) {
            return Observable.just(context.getString(R.string.girl_formate_not_allow))
        }
        val future = GlideApp
                .with(context)
                .asFile()
                .load(url)
                .submit()
        var file = future.get()

        // 首先保存图片
        val pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile()

        val appDir = File(pictureFolder, "Cybird")
        if (!appDir.exists()) {
            appDir.mkdirs()
        }
        val fileName = System.currentTimeMillis().toString() + ".jpg"
        val destFile = File(appDir, fileName)

        //TODO:这里去掉了原来的blankJ的Util，因为里面很多Utils没有用到导致方法很多需要分包
//        FileUtils.copyFile(file, destFile, object : FileUtils.OnReplaceListener {
//            override fun onReplace(): Boolean {
//                return true
//            }
//        })
        // 最后通知图库更新
        context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(File(destFile.path))))
        return Observable.just(context.getString(R.string.girl_success))
    }
}