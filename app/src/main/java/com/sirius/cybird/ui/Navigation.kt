package com.sirius.cybird.ui

import android.content.Context
import android.content.Intent
import com.sirius.cybird.ui.daily.DailyDetailActivity

/**
 *Created by Botasky on 2018/5/1
 */
object Navigation {
    val EXTRA_ID = "id"



    fun startDailyDetail(context: Context,id:Int){
        val intent = Intent(context, DailyDetailActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        context.startActivity(intent)
    }
}