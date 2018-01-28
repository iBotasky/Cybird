package com.sirius.cybird.di.singleton

import com.sirius.cybird.utils.SPUtils

/**
 *
 *Create By Botasky 28/01/2018
 **/
object SPManager {
    private val KEY_TEST = "test"


    fun setTest(value: String){
        SPUtils.setString(KEY_TEST, value)
    }

    fun getTest():String{
        return SPUtils.getString(KEY_TEST,"")
    }
}