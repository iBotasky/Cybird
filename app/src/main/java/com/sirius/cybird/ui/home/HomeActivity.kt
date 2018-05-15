package com.sirius.cybird.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.blankj.utilcode.util.ToastUtils
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.ui.base.BaseNavActivity
import com.sirius.cybird.ui.daily.DailyFragment
import com.sirius.cybird.ui.girls.GirlsFragment
import com.sirius.cybird.ui.mine.MineFragment
import com.sirius.cybird.ui.movie.MovieFragment
import com.tbruyelle.rxpermissions2.RxPermissions


class HomeActivity : BaseNavActivity() {
    private var mCurrentBackPressedTime = 0L
    private val BACK_PRESSED_INTERVAL = 2000L
    lateinit var mHomeBinding: ActivityHomeBinding
    lateinit var mTitleResources: List<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHomeBinding = getBaseViewBinding()
        mActivityComponent.inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        setToolbarTitle(mTitleResources[0])
        requestPermission()
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }


    fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (!isGranted(Manifest.permission.READ_PHONE_STATE) || !isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE))) {
            val rxPermissions = RxPermissions(this)
            rxPermissions.request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .compose(bindToLifecycle())
                    .subscribe()
        }
    }

    private fun isGranted(permission: String): Boolean {
        val checkSelfPermission = ActivityCompat.checkSelfPermission(this, permission)
        return checkSelfPermission == PackageManager.PERMISSION_GRANTED
    }


    override fun getBottomNavDatas(): List<NavItemData> {
        mTitleResources = listOf(R.string.tab_movie, R.string.tab_daily, R.string.tab_girls, R.string.tab_mine)
        return listOf(
                NavItemData(BottomNavigationItem(R.mipmap.ic_movie, R.string.tab_movie).setActiveColorResource(R.color.color_movie), MovieFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_book, R.string.tab_daily).setActiveColorResource(R.color.color_daily), DailyFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), GirlsFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_mine), MineFragment::class.java)
        )
    }

    override fun onNavigationChange(position: Int) {
        setToolbarTitle(mTitleResources[position])
    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - mCurrentBackPressedTime >= BACK_PRESSED_INTERVAL) {
            mCurrentBackPressedTime = System.currentTimeMillis()
            ToastUtils.showShort(R.string.g_click_to_finish)
        } else {
            finish()
        }
    }

    override fun initializeInjector() {

    }
}