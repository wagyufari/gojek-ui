package com.mayburger.gojekuiclone.ui.main.fragments.services

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.ui.main.MainNavigator
import com.mayburger.gojekuiclone.ui.main.fragments.promos.MainPromosNavigator
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class MainServicesViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainServicesNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}