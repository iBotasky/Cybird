package com.sirius.cybird.repository

import com.google.gson.Gson
import com.sirius.cybird.di.model.ApplicationModule
import com.sirius.cybird.di.model.NetModule
import com.sirius.cybird.module.TestInject
import com.sirius.cybird.net.HostSelectionInterceptor
import com.sirius.cybird.ui.home.HomePresenter
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Named
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
interface RepositoryComponent {

    fun getRetorofit(): Retrofit                             //开放Module中Retrofit的依赖

    fun getHostSelectionInterceptor(): HostSelectionInterceptor //开放Module中的HostSelectionInterceptor的依赖

    @Named("douban")
    fun getDoubanRetrofit(): Retrofit

    @Named("gank")
    fun getGankRetrofit(): Retrofit
}