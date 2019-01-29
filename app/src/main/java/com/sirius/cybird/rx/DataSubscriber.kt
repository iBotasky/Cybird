package com.sirius.cybird.rx

import android.content.Context
import com.sirius.cybird.net.error.ExceptionHandler
import com.sirius.cybird.utils.NetWorkUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast

/**
 *
 *Create by Botasky 2019/1/29
 */
abstract class DataSubscriber<T>(val context: Context): Observer<T>{
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        if (!NetWorkUtils.isConnected(context)){
            context.toast("网络不可用，请检查当前网络")
            if (!d.isDisposed) d.dispose()
        }
    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        context.toast(ExceptionHandler.handleExceptions(e))
    }


}