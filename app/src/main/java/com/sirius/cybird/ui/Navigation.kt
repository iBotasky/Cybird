package com.sirius.cybird.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.sirius.cybird.ui.daily.DailyDetailActivity
import com.sirius.cybird.ui.movie.detail.MovieDetailActivity
import com.sirius.cybird.ui.photo.PhotoViewActivity
import java.util.*


/**
 *Created by Botasky on 2018/5/1
 */
object Navigation {
    val EXTRA_ID = "id"
    val EXTRA_IMG = "img_url"
    val EXTRA_DATA = "data"
    val EXTRA_INDEX = "index"
    val EXTRA_URL = "url"

    /**
     * 知乎日报详情页
     */
    fun startDailyDetail(context: Context, id: Int, url: String) {
        val intent = Intent(context, DailyDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        intent.putExtra(EXTRA_IMG, url)
        context.startActivity(intent)
    }

    /**
     * 豆瓣电影详情页
     */
    fun startFilmDetail(context: Context, id: String, poster: String) {
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        intent.putExtra(EXTRA_IMG, poster)
        context.startActivity(intent)
    }

    /**
     * 图片查看大图
     */
    fun startPhotosView(context: Context, data: ArrayList<String>, dataId: ArrayList<String>, index: Int) {
        val intent = Intent(context, PhotoViewActivity::class.java)
        intent.putStringArrayListExtra(EXTRA_DATA, data)
        intent.putStringArrayListExtra(EXTRA_ID, dataId)
        intent.putExtra(EXTRA_INDEX, index)
        context.startActivity(intent)
    }

    /**
     * 调起系统浏览器打开网址
     */
    fun startBrowser(context: Context, url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

}