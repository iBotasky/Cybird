package com.sirius.cybird

import android.app.Application
import com.facebook.stetho.Stetho
import com.sirius.cybird.db.MyObjectBox
import io.objectbox.BoxStore

/**
 *
 *Create By Botasky 20/01/2018
 **/
class CybirdApplication : Application() {
    lateinit var mBoxStore : BoxStore
        set

    override fun onCreate() {
        super.onCreate()
        setupStetho()
        setupObjectBox()
    }

    fun setupStetho() {
        Stetho.initializeWithDefaults(this)
    }

    fun setupObjectBox() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build()
    }

    fun getBoxStore() : BoxStore{
        return this.mBoxStore
    }
}