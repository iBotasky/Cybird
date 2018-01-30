package com.sirius.cybird.ui.activitys.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR

/**
 * Description：
 * Created by Botasky on 2017/12/26.
 */
data class MainViewModel(
        private var _name: String
) : BaseObservable() {
    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    fun changeName() {
        if (name.equals("Botasky")) {
            name = "BOTASKY"
        } else {
            name = "Botasky"
        }
    }

}