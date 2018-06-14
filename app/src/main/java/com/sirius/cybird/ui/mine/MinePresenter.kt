package com.sirius.cybird.ui.mine

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.OneApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MinePresenter {
    val mRetrofit: Retrofit
    val mServiece: OneApi

    @Inject
    constructor(@Named(NameConst.ONE) retrofit: Retrofit) {
        mRetrofit = retrofit
        mServiece = retrofit.create(OneApi::class.java)
    }

    fun getLastDayInfo() {
        mServiece.getOneListId()
                .map { data -> data.data[0] }
    }
}