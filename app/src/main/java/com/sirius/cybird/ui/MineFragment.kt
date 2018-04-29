package com.sirius.cybird.ui

import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.centent.hh.b.HHType
import com.centent.hh.b.mian.CenBanner
import com.centent.hh.b.mian.CenBannerListener
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMineBinding
import com.sirius.cybird.ui.base.BaseFragment
import com.sirius.cybird.ui.home.HomeActivity

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
    }

    override fun onPause() {
        super.onPause()
        cenBanner.onPause(context)
    }

    override fun onResume() {
        super.onResume()
        cenBanner.onResume(context)

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