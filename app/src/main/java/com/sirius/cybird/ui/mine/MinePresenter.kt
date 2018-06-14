package com.sirius.cybird.ui.mine

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.OneApi
import com.sirius.cybird.net.response.OneDetailData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MinePresenter {
    private val mRetrofit: Retrofit
    private val mServiece: OneApi

    @Inject
    constructor(@Named(NameConst.ONE) retrofit: Retrofit) {
        mRetrofit = retrofit
        mServiece = retrofit.create(OneApi::class.java)
    }

    fun getLastDayInfo() : Observable<OneDetailData> {
        return mServiece.getOneListId()
                .flatMap { data -> mServiece.getOneDetailById(data.data[0]) }

    }
}