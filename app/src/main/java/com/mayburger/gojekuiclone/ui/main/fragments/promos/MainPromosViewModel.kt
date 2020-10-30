package com.mayburger.gojekuiclone.ui.main.fragments.promos

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class MainPromosViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainPromosNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}