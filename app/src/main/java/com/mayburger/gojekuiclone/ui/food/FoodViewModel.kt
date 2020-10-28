package com.mayburger.gojekuiclone.ui.food

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimatedVectorDrawable
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.databinding.ObservableArrayList
import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.ui.main.MainNavigator
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import kotlinx.android.synthetic.main.activity_food.*


class FoodViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<FoodNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }



}