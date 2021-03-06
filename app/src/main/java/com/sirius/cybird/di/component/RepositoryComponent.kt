package com.sirius.cybird.di.component

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.di.module.ApplicationModule
import com.sirius.cybird.di.module.NetModule
import com.sirius.cybird.net.HostSelectionInterceptor
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
@Component(modules = [ApplicationModule::class, NetModule::class])
interface RepositoryComponent {

    fun getHostSelectionInterceptor(): HostSelectionInterceptor //开放Module中的HostSelectionInterceptor的依赖

    @Named(NameConst.JUHE)
    fun getJuHeRetorofit(): Retrofit                             //开放Module中Retrofit的依赖

    @Named(NameConst.DOUBAN)
    fun getDoubanRetrofit(): Retrofit

    @Named(NameConst.GANK)
    fun getGankRetrofit(): Retrofit

    @Named(NameConst.ZHIHU)
    fun getZhiHuRetrofit(): Retrofit

    @Named(NameConst.ONE)
    fun getOneRetrofit():Retrofit
}