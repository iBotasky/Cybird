package com.sirius.cybird.ui

import android.app.Instrumentation
import android.content.Intent
import android.os.SystemClock
import android.view.MotionEvent
import com.blankj.utilcode.util.LogUtils
import com.centent.hh.b.HHType
import com.centent.hh.b.mian.CenBanner
import com.centent.hh.b.mian.CenBannerListener
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMineBinding
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseFragment
import com.sirius.cybird.ui.home.HomeActivity
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created By Botasky 28/04/2018
 */
class MineFragment : BaseFragment() {
    lateinit var cenBanner: CenBanner

    lateinit var mMineBinding: FragmentMineBinding


    override fun setupViews() {
        super.setupViews()
        mMineBinding = getBaseViewBinding()
        cenBanner = CenBanner(context, mMineBinding.llBanner, object : CenBannerListener {
            override fun onSuccess() {
                LogUtils.e("SUCCESS")
            }

            override fun onPause() {
                LogUtils.e("PAUSE")
            }

            override fun onClicked() {
                LogUtils.e("CLICKED")
            }

            override fun onNoAd() {
                LogUtils.e("NOAD")
                val intent = Intent(context, HomeActivity::class.java)
                startActivity(intent)
            }

            override fun onError() {
                LogUtils.e("ERROR")
            }

        }, HHType.BIG_BANNER, false)
//        mMineBinding.llBanner.setOnClickListener({
//            LogUtils.e(" view click")
//        })

//        mMineBinding.llBanner.performClick()

//        Observable.interval(0, 5, TimeUnit.SECONDS)
//                .compose(bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe({along-> onMock()})
    }

    private fun onMock() {
        LogUtils.e("ONMOCK")
        val mInst:Instrumentation = Instrumentation()
        mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 490.toFloat(), 440.toFloat(), 0))
        mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 490.toFloat(), 490.toFloat(), 0))
    }

    override fun onPause() {
        super.onPause()
        cenBanner.onPause(context)
    }

    override fun onResume() {
        super.onResume()
        cenBanner.onResume(context)
        Observable.interval(0, 20, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({along-> onMock()})
    }

    override fun onDestroy() {
        super.onDestroy()
        cenBanner.onDestory(context)
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_mine

    }

    override fun initializeInjector() {

    }
}