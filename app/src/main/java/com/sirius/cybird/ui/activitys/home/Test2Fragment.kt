package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.R

/**
 *
 *Create By Botasky 03/02/2018
 **/
class Test2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home2, container, false)//把layout布局文件转换成View对象
        return view

    }

}