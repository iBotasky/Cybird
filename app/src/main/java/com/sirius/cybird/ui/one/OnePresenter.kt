package com.sirius.cybird.ui.one

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.OneApi
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.utils.DateUtils
import io.reactivex.Observable
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 *
 *Create by Botasky 2018/8/30
 */
class OnePresenter @Inject constructor(@Named(NameConst.ONE) retrofit: Retrofit) {
    private val mRetrofit: Retrofit = retrofit
    private val mServiece: OneApi = retrofit.create(OneApi::class.java)
    private var mCountDate: Date = Date()


    fun refresh(){
        this.mCountDate = Date()
    }

    fun getLast7DayDetail(): Observable<List<OneDetailData.Data.Content>> {
        return Observable.fromIterable(DateUtils.getLast7DaysDate(mCountDate))
                .flatMap { time -> mServiece.getOneDetailByDate(time) }
                .flatMap { detail -> Observable.just(detail.data.contentList[0]) }
                .doFinally { mCountDate = Date(mCountDate.time - 7 * 24 * 60 * 60 * 1000) }
                .toList()
                .toObservable()
    }

}