package com.app.virtusa.viewModel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.app.virtusa.BR
import com.app.virtusa.utils.Status

/**
 *  Will be responsible monitor network response on viewModel
 */
class ResponseStatus: BaseObservable() {

    @get:Bindable
    var status: Status = Status.LOADING
        set(value) {
            field = value
            notifyPropertyChanged(BR.status)
        }

}