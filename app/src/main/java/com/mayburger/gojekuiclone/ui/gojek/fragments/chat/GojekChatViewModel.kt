package com.mayburger.gojekuiclone.ui.gojek.fragments.chat

import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class GojekChatViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<GojekChatNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }

}