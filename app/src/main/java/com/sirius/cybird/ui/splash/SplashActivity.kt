package com.sirius.cybird.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.animation.AlphaAnimation
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivitySplashBinding
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.main.MainActivity
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.ui.home.HomeActivity
import io.reactivex.Observable
import java.lang.ref.WeakReference

class SplashActivity : BaseActivity() {
    companion object {
        private val SPLASH_DELAY_MILLIONS: Long = 3500
        private val SPLASH_GO_HOME = 1000
        private val SPLASH_GO_GUIDE = 1001

        private class SplashHandler(activity: SplashActivity) : Handler() {
            internal var weakRef: WeakReference<SplashActivity>

            init {
                this.weakRef = WeakReference(activity)
            }

            override fun handleMessage(msg: Message?) {
                val activity = weakRef.get() ?: return
                when (msg?.what) {
                    SPLASH_GO_HOME -> activity.goHome()
                    SPLASH_GO_GUIDE -> activity.goGuide()
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
        mHandler.sendEmptyMessageDelayed(SPLASH_GO_HOME, SPLASH_DELAY_MILLIONS)

        mSplashBinding = getBaseViewBinding()

        animation(mSplashBinding.tvChinese, 1500)
        animation(mSplashBinding.tvFenlan, 1000)
        animation(mSplashBinding.tvGerman, 2000)
        animation(mSplashBinding.tvJp, 550)
        animation(mSplashBinding.tvItalian, 800)
        animation(mSplashBinding.tvMongolian, 2500)
        animation(mSplashBinding.tvEnglish, 3500)
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

    fun goHome() {
        val goHomeIntent = Intent(this, HomeActivity::class.java)
        startActivity(goHomeIntent)
        mHandler.removeCallbacksAndMessages(null)
        finish()
    }

    fun goGuide() {

    }

    override fun initializeInjector() {
    }
}