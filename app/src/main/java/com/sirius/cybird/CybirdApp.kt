package com.sirius.cybird

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.sirius.cybird.config.Config
import com.sirius.cybird.db.MyObjectBox
import com.sirius.cybird.di.module.ApplicationModule
import com.sirius.cybird.di.module.NetModule
import com.sirius.cybird.repository.DaggerRepositoryComponent
import com.sirius.cybird.repository.RepositoryComponent
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

/**
 *
 *Create By Botasky 20/01/2018
 **/

class CybirdApp : Application() {
    companion object {
        lateinit var mAppContext: CybirdApp
        lateinit var mBoxStore: BoxStore
        lateinit var mRepositoryComponent: RepositoryComponent
        fun getContext(): Context {
            return mAppContext.applicationContext
        }
        fun getBoxStore(): BoxStore {
            return mBoxStore
        }
        fun getRepostitoryComponent(): RepositoryComponent{
            return mRepositoryComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = this
        setupStetho()
        setupObjectBox()
        setupComponent()
        setupUtils()
        setupAds()
    }

    fun setupAds(){
    }

    fun setupUtils(){
        Utils.init(this)
    }

    fun setupComponent(){
        mRepositoryComponent = DaggerRepositoryComponent.builder()
                .applicationModule(ApplicationModule(this))
                .netModule(NetModule())
                .build()
    }

    fun setupStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())
    }

    fun setupObjectBox() {
        mBoxStore = MyObjectBox.builder().androidContext(this).build()
        if (BuildConfig.DEBUG){
            AndroidObjectBrowser(mBoxStore).start(this)
        }
    }
}