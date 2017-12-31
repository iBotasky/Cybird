package com.sirius.cybird.ui.activitys.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Create by Botasky
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel("Botasky")
        mBinding?.viewModel = viewModel
        mBinding?.executePendingBindings()

        button.setOnClickListener({
            FilmsApi.getFilmsService()
                    .getComingSoon(0, 10)
                    .compose(bindToLifecycle())
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .subscribe({ filmsData ->
                        viewModel.name = filmsData.title
                    }, { e ->
                        Log.e("TAG", e.message)
                    })
        })
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

}
