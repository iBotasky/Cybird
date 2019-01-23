package com.sirius.cybird.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityHomeBinding
import com.sirius.cybird.module.NavItemData
import com.sirius.cybird.net.url.Urls
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseNavActivity
import com.sirius.cybird.ui.daily.DailyFragment
import com.sirius.cybird.ui.girls.GirlsFragment
import com.sirius.cybird.ui.movie.MovieFragment
import com.sirius.cybird.ui.one.OneFragment
import com.sirius.cybird.utils.GlideUtil
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

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
    }

    private fun setNavigationView() {
        //禁止手势滑动
        //mHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        //打开手势滑动
        //mHomeBinding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        mHomeBinding.navigation.setCheckedItem(R.id.nav_one)
        mHomeBinding.navigation.setNavigationItemSelectedListener {
            mHomeBinding.drawerLayout.closeDrawers()
            when (it.itemId) {
                R.id.nav_one, R.id.nav_movie, R.id.nav_daily, R.id.nav_girls -> {
                    it.isChecked = true
                    when (it.itemId) {
                        R.id.nav_one -> mHomeBinding.idBottomNavBar.selectTab(0)
                        R.id.nav_movie -> mHomeBinding.idBottomNavBar.selectTab(1)
                        R.id.nav_daily -> mHomeBinding.idBottomNavBar.selectTab(2)
                        R.id.nav_girls -> mHomeBinding.idBottomNavBar.selectTab(3)
                    }
                }
                R.id.nav_blog, R.id.nav_login -> {
                    it.isChecked = false
                    when (it.itemId) {
                        R.id.nav_blog -> Navigation.startBrowser(this@HomeActivity, Urls.URL_BLOG)
                        R.id.nav_login -> startActivity(AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(arrayListOf(
                                        AuthUI.IdpConfig.EmailBuilder().build(),
                                        AuthUI.IdpConfig.PhoneBuilder().build(),
                                        AuthUI.IdpConfig.GoogleBuilder().build())
                                )
                                .setLogo(R.mipmap.ic_launcher_round)
                                .setTheme(R.style.LoginTheme)
                                .build())
                    }

                }
            }
            false
        }

        val headView = mHomeBinding.navigation.getHeaderView(0)
        val headBg: ImageView = headView.findViewById(R.id.iv_head_bg)
        val headAvatar: ImageView = headView.findViewById(R.id.iv_head_avatar)
        val headName: TextView = headView.findViewById(R.id.tv_head_username)
        GlideUtil.loadLocalImage(this, headBg, R.drawable.bg_drawer)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            headName.text = user.displayName
            GlideUtil.loadAvatar(this, headAvatar, user.photoUrl.toString())
        } else {
            headName.text = getString(R.string.g_click_to_sign_in)
        }

        headAvatar.setOnClickListener(({
            if (FirebaseAuth.getInstance().currentUser != null) {
//                AuthUI.getInstance().signOut(this).addOnCompleteListener(({
//                    headName.text =  getString(R.string.g_click_to_sign_in)
//                    GlideUtil.loadLocalImage(this@HomeActivity, headAvatar, R.mipmap.ic_launcher_round)
//                }))

                Navigation.startSetting(this)

            } else {
                alert(R.string.alert_title, R.string.alert_message) {
                    yesButton { Navigation.startLogin(this@HomeActivity, Navigation.SIGN_IN) }
                    noButton { }
                }.show()

            }
        }))
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Navigation.SIGN_IN && resultCode == Activity.RESULT_OK) {
            val headView = mHomeBinding.navigation.getHeaderView(0)
            val headAvatar: ImageView = headView.findViewById(R.id.iv_head_avatar)
            val headName: TextView = headView.findViewById(R.id.tv_head_username)
            headName.text = FirebaseAuth.getInstance().currentUser?.displayName
            GlideUtil.loadAvatar(this, headAvatar, FirebaseAuth.getInstance().currentUser?.photoUrl.toString())
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_home
    }

    override fun getBottomNavDatas(): List<NavItemData> {
        mTitleResources = listOf(R.string.tab_mine, R.string.tab_movie, R.string.tab_daily, R.string.tab_girls)
        return listOf(
                NavItemData(BottomNavigationItem(R.drawable.ic_account, R.string.tab_mine).setActiveColorResource(R.color.color_000000), OneFragment::class.java),
                NavItemData(BottomNavigationItem(R.drawable.ic_movie, R.string.tab_movie).setActiveColorResource(R.color.color_movie), MovieFragment::class.java),
                NavItemData(BottomNavigationItem(R.drawable.ic_book, R.string.tab_daily).setActiveColorResource(R.color.color_daily), DailyFragment::class.java),
                NavItemData(BottomNavigationItem(R.drawable.ic_whatshot, R.string.tab_girls).setActiveColorResource(R.color.color_girl), GirlsFragment::class.java)
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
            toast(R.string.g_click_to_finish)
        } else {
            finish()
        }
    }

    override fun initializeInjector() {

    }
}