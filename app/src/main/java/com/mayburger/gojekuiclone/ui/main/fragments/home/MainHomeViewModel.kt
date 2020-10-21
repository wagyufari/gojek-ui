package com.mayburger.gojekuiclone.ui.main.fragments.home

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class MainHomeViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainHomeNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}