package com.sirius.cybird.ui.photo

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.sirius.cybird.BR
import org.jetbrains.annotations.NotNull

/**
 * Created By Botasky 2018/5/17
 */
class PhotoViewModel(@NotNull current: Int, @NotNull total: Int) : BaseObservable() {
    var index: Int = 0
        set(value) {
            field = value
            title = (index + 1).toString() + "/" + total
        }
    var total: Int

    var title: String = ""
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    init {
        index = current
        this.total = total
        title = (index + 1).toString() + "/" + total
    }


}