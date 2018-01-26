package com.sirius.cybird

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.sirius.cybird.db.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

/**
 *
 *Create By Botasky 20/01/2018
 **/
class CybirdApplication : Application() {
    companion object {
        lateinit var mAppContext: CybirdApplication
        lateinit var mBoxStore: BoxStore
        fun getContext(): Context {
            return mAppContext.applicationContext
        }
        fun getBoxStore(): BoxStore {
            return mBoxStore
        }
    }


    override fun onCreate() {
        super.onCreate()
        mAppContext = this
        setupStetho()
        setupObjectBox()
    }

    fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    fun setupObjectBox() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build()
        if (BuildConfig.DEBUG){
            AndroidObjectBrowser(mBoxStore).start(this)
        }
    }

}