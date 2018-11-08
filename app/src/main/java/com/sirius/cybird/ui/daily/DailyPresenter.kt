package com.sirius.cybird.ui.daily

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.ZhiHuApi
import com.sirius.cybird.net.response.TopStories
import com.sirius.cybird.net.response.ZhiHuData
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 *Created by Botasky on 2018/4/29
 */
class DailyPresenter @Inject constructor(@Named(NameConst.ZHIHU) retrofit: Retrofit) {

    val mRetrofit: Retrofit = retrofit

    fun getDailyStories(): Observable<List<MultiItemEntity>> {
        return mRetrofit.create(ZhiHuApi::class.java)
                .getLastNews()
                .flatMap { zhihudata -> Observable.just(convertToMultiEntity(zhihudata)) }
    }

    private fun convertToMultiEntity(zhiHuData: ZhiHuData): List<MultiItemEntity> {
        val multiList: MutableList<MultiItemEntity> = mutableListOf()
        multiList.add(TopStories(zhiHuData.topStories))
        multiList.addAll(zhiHuData.stories)
        return multiList
    }

}