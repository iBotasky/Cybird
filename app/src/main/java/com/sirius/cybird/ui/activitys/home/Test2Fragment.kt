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
        layoutInflater.inflate(R.layout.fragment_home2,container)
        return super.onCreateView(inflater, container, savedInstanceState)

    }

}