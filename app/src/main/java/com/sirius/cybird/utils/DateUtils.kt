package com.sirius.cybird.utils

import java.text.SimpleDateFormat
import java.util.*


/**
 *
 *Create by Botasky 2018/8/30
 */
object DateUtils {

    //获取过去7天的日期
    fun getLast7Days(): List<String> {
        val list: MutableList<String> = mutableListOf()
        val calendar = Calendar.getInstance()
        val format = SimpleDateFormat("yyyy-MM-dd")
        for (past in 0..6) {
            if (past == 0)
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR))
            else
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1)

            val today = calendar.time
            list.add(format.format(today))
        }
        return list.toList()
    }


    fun getLast7DaysDate(date: Date): List<String> {
        val list = mutableListOf<String>()
        val calendar = dateToCalendar(date)
        val format = SimpleDateFormat("yyyy-MM-dd")
        for (past in 0..6){
            if (past != 0){
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1)
            }
            list.add(format.format(calendar.time))
        }
        return list.toList()
    }


    fun dateToCalendar(date: Date): Calendar {
        return with(date) {
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar
        }
    }

    fun calendareToDate(year:Int, month:Int, day:Int):Date{
        val calendar = Calendar.getInstance()
        calendar.set(year, month -1 , day)
        return with(calendar){
            time
        }
    }
}