package com.sirius.cybird.ui

import android.content.Context
import android.content.Intent
import com.sirius.cybird.ui.daily.DailyDetailActivity
import com.sirius.cybird.ui.movie.detail.MovieDetailActivity
import com.sirius.cybird.ui.photo.PhotoViewActivity

/**
 *Created by Botasky on 2018/5/1
 */
object Navigation {
    val EXTRA_ID = "id"
    val EXTRA_IMG = "img_url"



    fun startDailyDetail(context: Context,id:Int, url:String){
        val intent = Intent(context, DailyDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        intent.putExtra(EXTRA_IMG, url)
        context.startActivity(intent)
    }

    fun startFilmDetail(context: Context, id: String, poster: String){
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        intent.putExtra(EXTRA_IMG, poster)
        context.startActivity(intent)
    }

    fun startPhotosView(context: Context){
        val intent = Intent(context, PhotoViewActivity::class.java)
        context.startActivity(intent)
    }
}