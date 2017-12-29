package com.sirius.cybird.ui.activitys.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.net.api.GirlsApi
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

//        button.setOnClickListener({
//            GirlsApi.getGirlsService()
//                    .accessGirls(1)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(
//                            { girls -> viewModel.name = girls.results[0].url },
//                            { error -> Log.e("TAG", error.message) },
//                            { Log.e("TAG", "onComplete")}
//                    )
//        })

        button.setOnClickListener({
            FilmsApi.getFilmsService()
                    .getComingSoon(0, 10)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ filmsData ->
                        viewModel.name = filmsData.title
                    }, { e ->
                        Log.e("TAG", e.message)
                    })
        })
    }


}
