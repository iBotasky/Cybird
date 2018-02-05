package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentHomeBinding
import com.sirius.cybird.ui.base.BaseFragment
import com.sirius.cybird.utils.ToastUtils

/**
 *
 *Create By Botasky 03/02/2018
 **/
class TestFragment:BaseFragment() {
    lateinit var mTestBinding: FragmentHomeBinding


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTestBinding = getBaseViewBinding()
        mTestBinding.button.setOnClickListener({ToastUtils.show("HELLO")})
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_home
    }

}