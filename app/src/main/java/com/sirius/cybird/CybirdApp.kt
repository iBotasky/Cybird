package com.sirius.cybird

import android.app.Application
import com.facebook.stetho.Stetho
import com.sirius.cybird.db.MyObjectBox
import io.objectbox.BoxStore

/**
 *
 *Create By Botasky 20/01/2018
 **/
class CybirdApp : Application() {
    lateinit var mBoxStore: BoxStore
        set

    override fun onCreate() {
        super.onCreate()
        setupStetho()
        setupObjectBox()
    }

    fun setupStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())
    }

    fun setupObjectBox() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build()
    }

    fun getBoxStore(): BoxStore {
        return this.mBoxStore
    }
}