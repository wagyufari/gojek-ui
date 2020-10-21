package com.mayburger.gojekuiclone.ui.main.fragments.chat

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class MainChatViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<MainChatNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}