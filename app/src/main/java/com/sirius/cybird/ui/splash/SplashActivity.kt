package com.sirius.cybird.ui.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.view.View
import android.view.animation.AlphaAnimation
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivitySplashBinding
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.ui.home.HomeActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.error
import org.jetbrains.anko.toast
import java.lang.ref.WeakReference

class SplashActivity : BaseActivity() {
    companion object {
        private const val SPLASH_DELAY_MILLIONS: Long = 3500
        private const val SPLASH_GO_HOME = 1000

        private class SplashHandler(activity: SplashActivity) : Handler() {
            internal var weakRef: WeakReference<SplashActivity> = WeakReference(activity)

            override fun handleMessage(msg: Message?) {
                val activity = weakRef.get() ?: return
                when (msg?.what) {
                    SPLASH_GO_HOME -> activity.goHome()
                }
                super.handleMessage(msg)
            }
        }
    }

    /**
     * Handler:跳转到不同界面
     */
    lateinit var mHandler: Handler
    lateinit var mSplashBinding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHandler = SplashHandler(this)
        mSplashBinding = getBaseViewBinding()
        requestPermission()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_splash
    }

    fun animation(view: View, duration: Long) {
        val animation = AlphaAnimation(0.toFloat(), 1.toFloat())
        animation.duration = duration
        animation.fillAfter = true
        view.startAnimation(animation)
    }

    private fun isGranted(permission: String): Boolean {
        val checkSelfPermission = ActivityCompat.checkSelfPermission(this, permission)
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (!isGranted(Manifest.permission.READ_PHONE_STATE) || !isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            val rxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .compose(bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            startAnimAndHandler()
                        } else {
                            toast(R.string.g_permission_not_allow)
                            finish()
                        }
                    }
        }else{
            startAnimAndHandler()
        }
    }

    fun startAnimAndHandler(){
        animation(mSplashBinding.tvChinese, 1500)
        animation(mSplashBinding.tvFenlan, 1000)
        animation(mSplashBinding.tvGerman, 2000)
        animation(mSplashBinding.tvJp, 550)
        animation(mSplashBinding.tvItalian, 800)
        animation(mSplashBinding.tvMongolian, 2500)
        animation(mSplashBinding.tvEnglish, 3500)
        mHandler.sendEmptyMessageDelayed(SPLASH_GO_HOME, SPLASH_DELAY_MILLIONS)
    }


    fun goHome() {
        error { "onGoHome" }
        val goHomeIntent = Intent(this, HomeActivity::class.java)
        startActivity(goHomeIntent)
        mHandler.removeCallbacksAndMessages(null)
        finish()
    }

    override fun initializeInjector() {
    }


    override fun onBackPressed() {

    }
}