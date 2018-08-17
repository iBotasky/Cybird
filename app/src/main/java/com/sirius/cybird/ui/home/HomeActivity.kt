package com.sirius.cybird.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.view.MenuItem
import android.widget.Toast
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.net.url.Urls
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseNavActivity
import com.sirius.cybird.ui.daily.DailyFragment
import com.sirius.cybird.ui.girls.GirlsFragment
import com.sirius.cybird.ui.mine.MineFragment
import com.sirius.cybird.ui.movie.MovieFragment
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 *Created by Botasky on 2018/4/30
 */
class HomeActivity : BaseNavActivity() {
    private var mCurrentBackPressedTime = 0L
    private val BACK_PRESSED_INTERVAL = 2000L
    lateinit var mHomeBinding: ActivityHomeBinding
    lateinit var mTitleResources: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent.inject(this)
    }

    override fun setupViews() {
        super.setupViews()
        mHomeBinding = getBaseViewBinding()
        setToolbarTitle(mTitleResources[0])
        setNavigationView()
        requestPermission()
    }

    private fun setNavigationView() {
        //禁止手势滑动
        //mHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        //打开手势滑动
        //mHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        mHomeBinding.navigation.setCheckedItem(R.id.nav_one)
        mHomeBinding.navigation.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                mHomeBinding.drawerLayout.closeDrawers()
                when (item.itemId) {
                    R.id.nav_one, R.id.nav_movie, R.id.nav_daily, R.id.nav_girls -> {
                        item.isChecked = true
                        when (item.itemId) {
                            R.id.nav_one -> mHomeBinding.idBottomNavBar.selectTab(0)
                            R.id.nav_movie -> mHomeBinding.idBottomNavBar.selectTab(1)
                            R.id.nav_daily -> mHomeBinding.idBottomNavBar.selectTab(2)
                            R.id.nav_girls -> mHomeBinding.idBottomNavBar.selectTab(3)
                        }
                    }
                    R.id.nav_blog -> {
                        item.isChecked = false
                        Navigation.startBrowser(this@HomeActivity, Urls.URL_BLOG)

                    }
                }
                return false
            }
        })
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }

    private fun requestPermission() {
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
        mTitleResources = listOf(R.string.tab_mine, R.string.tab_movie, R.string.tab_daily, R.string.tab_girls)
        return listOf(
                NavItemData(BottomNavigationItem(R.mipmap.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_000000), MineFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_movie, R.string.tab_movie).setActiveColorResource(R.color.color_movie), MovieFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_book, R.string.tab_daily).setActiveColorResource(R.color.color_daily), DailyFragment::class.java),
                NavItemData(BottomNavigationItem(R.mipmap.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), GirlsFragment::class.java)
        )
    }

    override fun onNavigationChange(position: Int) {
        setToolbarTitle(mTitleResources[position])
        mHomeBinding.navigation.setCheckedItem(
                when (position) {
                    0 -> R.id.nav_one
                    1 -> R.id.nav_movie
                    2 -> R.id.nav_daily
                    3 -> R.id.nav_girls
                    else -> R.id.nav_one
                }
        )
    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - mCurrentBackPressedTime >= BACK_PRESSED_INTERVAL) {
            mCurrentBackPressedTime = System.currentTimeMillis()
//            ToastUtils.showShort(R.string.g_click_to_finish)
            Toast.makeText(this, R.string.g_click_to_finish, Toast.LENGTH_SHORT).show();
        } else {
            finish()
        }
    }

    override fun initializeInjector() {

    }
}