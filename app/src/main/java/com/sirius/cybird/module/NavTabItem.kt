package com.sirius.cybird.module

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationItem

/**
 * Indicator对应的Items
 *Create By Botasky 03/02/2018
 **/
data class TabItemData(@StringRes val titleId: Int, val clazz: Class<out Fragment>) {
    constructor(@StringRes titleId: Int, @DrawableRes iconIdRes: Int, clazz: Class<out Fragment>) : this(titleId, clazz)
    constructor(@StringRes titleId: Int, @DrawableRes selectRes: Int, @DrawableRes unselectRes: Int, clazz: Class<out Fragment>) : this(titleId, clazz)

    fun instantiate(context: Context): Fragment {
        return Fragment.instantiate(context, clazz.name)
    }
}

data class NavItemData(val item: BottomNavigationItem, val clazz: Class<out Fragment>){
    fun instantiate(context: Context): Fragment{
        return Fragment.instantiate(context, clazz.name)
    }
}

