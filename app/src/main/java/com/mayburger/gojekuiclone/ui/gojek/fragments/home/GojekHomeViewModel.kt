package com.mayburger.gojekuiclone.ui.gojek.fragments.home

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class GojekHomeViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<GojekHomeNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

    fun onClickPay(){
        navigator?.onClickPay()
    }

}