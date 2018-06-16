package com.sirius.cybird.ui

import android.content.Context
import android.content.Intent
import com.sirius.cybird.ui.daily.DailyDetailActivity
import com.sirius.cybird.ui.movie.detail.MovieDetailActivity
import com.sirius.cybird.ui.photo.PhotoViewActivity
import java.util.ArrayList

/**
 *Created by Botasky on 2018/5/1
 */
object Navigation {
    val EXTRA_ID = "id"
    val EXTRA_IMG = "img_url"
    val EXTRA_DATA = "data"
    val EXTRA_INDEX = "index"



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

    fun startPhotosView(context: Context, data: ArrayList<String>, index:Int){
        val intent = Intent(context, PhotoViewActivity::class.java)
        intent.putStringArrayListExtra(EXTRA_DATA, data)
        intent.putExtra(EXTRA_INDEX, index)
        context.startActivity(intent)
    }
}