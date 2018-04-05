package com.sirius.cybird.module

import android.content.Context
import com.sirius.cybird.di.qualifier.ForApplication
import javax.inject.Inject

class TestInject {
    val mContext: Context

    @Inject
    constructor(@ForApplication context: Context){
        mContext = context
    }
}