package com.sirius.cybird.ui.girls

import com.sirius.cybird.di.NameConst
import com.sirius.cybird.net.api.GirlsApi
import com.sirius.cybird.net.response.ResultsBean
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

/**
 *Created by Botasky on 2018/4/30
 */
class GirlsPresenter {
    val mRetrofit:Retrofit
    @Inject
    constructor(@Named(NameConst.GANK) retrofit: Retrofit){
        mRetrofit = retrofit
    }

    fun getGirls(page:Int): Observable<List<ResultsBean>> {
        return mRetrofit.create(GirlsApi::class.java)
                .accessGirls(page)
                .flatMap { girlResopnse-> Observable.just(girlResopnse.results)}
    }
}