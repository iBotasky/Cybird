package com.sirius.cybird.ui.activitys.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding

/**
 * Create by Botasky
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = MainViewModel("Botasky")
        mBinding.executePendingBindings()
    }
}
