package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.sirius.cybird.R

/**
 *
 *Create By Botasky 03/02/2018
 **/
class TestFragment:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.view?.setBackgroundColor(activity?.resources?.getColor(R.color.colorPrimary)!!)
    }
}