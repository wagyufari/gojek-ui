package com.mayburger.gojekuiclone.ui.gojek.fragments.services

import androidx.databinding.ObservableField
import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.models.events.TransitionEvent
import com.mayburger.gojekuiclone.util.ext.ViewUtils.dpToPx
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider


class GojekServicesViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<GojekServicesNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
        when (obj) {
            is TransitionEvent -> {
                titleHeight.set(obj.p3 * dpToPx(32))
                if (obj.p3 > 0.2) {
                    titleMarginTop.set(obj.p3 * dpToPx(8))
                }
            }
        }
    }

    val titleHeight = ObservableField(0f)
    val titleMarginTop = ObservableField(0f)

}