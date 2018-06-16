package com.sirius.cybird.ui.daily

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.ZhiHuApi
import com.sirius.cybird.net.response.ZHNewsDetailData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 *Created by Botasky on 2018/5/1
 */
class DailyDetailPresenter {

    val mRetrofit: Retrofit

    @Inject
    constructor(@Named(NameConst.ZHIHU) retrofit: Retrofit) {
        mRetrofit = retrofit
    }

    fun getDailyDetail(id: Int): Observable<ZHNewsDetailData> {
        return mRetrofit.create(ZhiHuApi::class.java)
                .getNewsDetail(id)
                .flatMap { data -> Observable.just(data) }
    }
}