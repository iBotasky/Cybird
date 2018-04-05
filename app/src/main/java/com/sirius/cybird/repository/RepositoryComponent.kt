package com.sirius.cybird.repository

import com.sirius.cybird.di.model.ApplicationModule
import com.sirius.cybird.di.model.NetModule
import com.sirius.cybird.module.TestInject
import com.sirius.cybird.ui.home.HomePresenter
import dagger.Component
import javax.inject.Singleton


/**
 * This is a Dagger component. Refer to {@link MyApplication} for the list of Dagger components
 * used in this application.
 * <p>
 * Even though Dagger allows annotating a {@link Component @Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * MyApplication}.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class))
interface RepositoryComponent{
    fun getHomePresenter():HomePresenter //对单个类进行inject,表示对此类公开可见
}