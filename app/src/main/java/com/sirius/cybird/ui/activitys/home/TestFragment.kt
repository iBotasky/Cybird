package com.sirius.cybird.ui.activitys.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentHomeBinding
import com.sirius.cybird.ui.base.BaseFragment

/**
 *
 *Create By Botasky 03/02/2018
 **/
class TestFragment:BaseFragment() {
    lateinit var mTestBinding: FragmentHomeBinding


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mTestBinding = getBaseViewBinding()
        mTestBinding.button.setOnClickListener({Snackbar.make(mTestBinding.root, R.string.app_home, Snackbar.LENGTH_SHORT).show()})
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_home
    }

}