package com.sirius.cybird.ui.activitys.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivitySplashBinding
import com.sirius.cybird.ui.activitys.main.MainActivity
import com.sirius.cybird.ui.base.BaseActivity
import java.lang.ref.WeakReference

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    companion object {
        private val SPLASH_DELAY_MILLIONS: Long = 10000
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHandler = SplashHandler(this)
        mHandler.sendEmptyMessageDelayed(SPLASH_GO_HOME, SPLASH_DELAY_MILLIONS)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_splash
    }

    fun goHome() {
        val goHomeIntent = Intent(this, MainActivity::class.java)
        startActivity(goHomeIntent)
        mHandler.removeCallbacksAndMessages(null)
        finish()
    }

    fun goGuide() {

    }
}