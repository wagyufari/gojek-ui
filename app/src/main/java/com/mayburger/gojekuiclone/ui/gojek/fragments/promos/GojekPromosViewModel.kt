package com.mayburger.gojekuiclone.ui.gojek.fragments.promos

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class GojekPromosViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<GojekPromosNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}