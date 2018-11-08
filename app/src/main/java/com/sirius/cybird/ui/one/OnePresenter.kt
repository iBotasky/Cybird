package com.sirius.cybird.ui.one

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.OneApi
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.utils.DateUtils
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 *
 *Create by Botasky 2018/8/30
 */
class OnePresenter @Inject constructor(@Named(NameConst.ONE) retrofit: Retrofit) {
    private val mRetrofit: Retrofit = retrofit
    private val mServiece: OneApi

    init {
        mServiece = retrofit.create(OneApi::class.java)
    }

    fun getLastDayInfo() : Observable<OneDetailData> {
        return mServiece.getOneDetail()
    }

    fun getLast7DayDetail(): Observable<List<OneDetailData.Data.Content>> {
        return Observable.fromIterable(DateUtils.getLast7Days())
                .flatMap { time -> mServiece.getOneDetailByDate(time) }
                .flatMap { detail -> Observable.just(detail.data.contentList[0]) }
                .toList()
                .toObservable()
    }

}