package com.sirius.cybird.ui.activitys.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding
import com.sirius.cybird.net.api.WiKiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by Botasky
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = MainViewModel("Botasky")
        mBinding.viewModel = viewModel
        mBinding.executePendingBindings()

        button.setOnClickListener {
            WiKiApiService.getWikiService()
                    .hitCountCheck("query", "json", "search", text_view.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( {result -> viewModel.name = result?.query?.searchInfo?.totalhits.toString()},
                            {error -> })
        }
    }


}
