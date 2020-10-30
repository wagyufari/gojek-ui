package com.mayburger.gojekuiclone.ui.loading

import androidx.databinding.ObservableArrayList
import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.ui.main.MainNavigator
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class LoadingBottomViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<LoadingBottomNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }


}