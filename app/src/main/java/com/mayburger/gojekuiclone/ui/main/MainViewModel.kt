package com.mayburger.gojekuiclone.ui.main

import androidx.databinding.ObservableArrayList
import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class MainViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

    var labels: ObservableArrayList<String> = ObservableArrayList()

    init {
        labels.add("Hello")
        labels.add("This")
        labels.add("Is")
        labels.add("My")
        labels.add("Parent's")
        labels.add("Account")
    }

}