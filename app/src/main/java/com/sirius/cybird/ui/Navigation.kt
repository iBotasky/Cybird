package com.sirius.cybird.ui

import android.app.Activity
import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.sirius.cybird.R
import com.sirius.cybird.ui.daily.DailyDetailActivity
import com.sirius.cybird.ui.movie.detail.MovieDetailActivity
import com.sirius.cybird.ui.photo.PhotoViewActivity
import org.jetbrains.anko.browse
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * 一些跳转操作，改用Anko的intent实现
 * https://github.com/Kotlin/anko/wiki/Anko-Commons-%E2%80%93-Intents
 *Created by Botasky on 2018/5/1
 */
object Navigation {
    val EXTRA_ID = "id"
    val EXTRA_IMG = "img_url"
    val EXTRA_DATA = "data"
    val EXTRA_INDEX = "index"
    val EXTRA_URL = "url"

    /**
     * 设置页面
     */
    fun startSetting(context: Context){
        context.startActivity<SettingActivity>()
    }

    /**
     * 知乎日报详情页
     */
    fun startDailyDetail(context: Context, id: Int, url: String) {
        context.startActivity<DailyDetailActivity>(EXTRA_ID to id, EXTRA_IMG to url)
    }

    /**
     * 豆瓣电影详情页
     */
    fun startFilmDetail(context: Context, id: String, poster: String) {
        context.startActivity<MovieDetailActivity>(EXTRA_ID to id, EXTRA_IMG to poster)
    }

    /**
     * 图片查看大图
     */
    fun startPhotosView(context: Context, data: ArrayList<String>, dataId: ArrayList<String>, index: Int) {
        context.startActivity<PhotoViewActivity>(EXTRA_DATA to data, EXTRA_ID to dataId, EXTRA_INDEX to index)
    }

    /**
     * 调起系统浏览器打开网址
     */
    fun startBrowser(context: Context, url: String) {
        context.browse(url)
    }


    /**
     * 跳转到Firebase的登录ui
     */
    val SIGN_IN = 9

    fun startLogin(context: Context, tag: Int = 0) {
        if (tag == 0) {
            context.startActivity(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.PhoneBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build())
                    )
                    .setLogo(R.mipmap.ic_launcher_round)
                    .build())
        } else {
            (context as Activity).startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setAvailableProviders(arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.PhoneBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build())
                    )
                    .setTheme(R.style.LoginTheme)
                    .setLogo(R.mipmap.ic_launcher_round)
                    .build(), tag)
        }
    }

}