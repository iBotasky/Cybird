package com.sirius.cybird.ui

import android.content.Context
import android.content.Intent
import com.sirius.cybird.ui.daily.DailyDetailActivity

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
}