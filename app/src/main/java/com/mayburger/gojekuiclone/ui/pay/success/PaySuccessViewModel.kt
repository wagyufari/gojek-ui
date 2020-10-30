package com.mayburger.gojekuiclone.ui.pay.success

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.models.events.BackEvent
import com.mayburger.gojekuiclone.ui.base.BaseViewModel
import com.mayburger.gojekuiclone.ui.pay.success.PaySuccessFragment
import com.mayburger.gojekuiclone.ui.pay.success.PaySuccessNavigator
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeHide
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeShow
import com.mayburger.gojekuiclone.util.ext.ViewUtils.scale
import com.mayburger.gojekuiclone.util.ext.ViewUtils.width
import com.mayburger.gojekuiclone.util.ext.ViewUtils.xToDp
import com.mayburger.gojekuiclone.util.ext.ViewUtils.yToDp
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import kotlinx.android.synthetic.main.fragment_pay_success.*


class PaySuccessViewModel @ViewModelInject constructor(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) :
    BaseViewModel<PaySuccessNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
        when (obj) {
            is BackEvent -> {
                if (obj.obj is PaySuccessFragment) {
                    navigator?.onBackPressed(obj.obj)
                }
            }
        }
    }

    var isAnimating = true

    companion object {
        fun PaySuccessFragment.playSuccessAnimation() {
            card.fadeShow(duration = 300)
            card.scale(1f, duration = 300, onEnd = {

                image.setImageResource(R.drawable.ic_done_green)
                val drawable = image.drawable
                if (drawable is AnimatedVectorDrawableCompat) {
                    drawable.start()
                } else if (drawable is AnimatedVectorDrawable) {
                    drawable.start()
                }
                card.scale(0.8f, duration = 300, onEnd = {

                    decor1.alpha = 1f
                    decor1.yToDp(-50f, duration = 300)
                    decor1.xToDp(50f, duration = 300)
                    decor1.width(80, 300, onEnd = {
                        decor1.width(30, 400)
                        decor1.yToDp(-70f, duration = 400)
                        decor1.xToDp(70f, duration = 400)
                        decor1.fadeHide(duration = 400)
                    })

                    decor2.alpha = 1f
                    decor2.yToDp(-50f, duration = 300)
                    decor2.width(80, 300, onEnd = {
                        decor2.width(30, 400)
                        decor2.yToDp(-70f, duration = 400)
                        decor2.fadeHide(duration = 400)
                    })

                    decor3.alpha = 1f
                    decor3.yToDp(-50f, duration = 300)
                    decor3.xToDp(-50f, duration = 300)
                    decor3.width(80, 300, onEnd = {
                        decor3.width(30, 400)
                        decor3.yToDp(-70f, duration = 400)
                        decor3.xToDp(-70f, duration = 400)
                        decor3.fadeHide(duration = 400)
                    })

                    decor4.alpha = 1f
                    decor4.xToDp(-50f, duration = 300)
                    decor4.width(80, 300, onEnd = {
                        decor4.width(30, 400)
                        decor4.xToDp(-70f, duration = 400)
                        decor4.fadeHide(duration = 400)
                    })

                    decor5.alpha = 1f
                    decor5.yToDp(50f, duration = 300)
                    decor5.xToDp(-50f, duration = 300)
                    decor5.width(80, 300, onEnd = {
                        decor5.width(30, 400)
                        decor5.yToDp(70f, duration = 400)
                        decor5.xToDp(-70f, duration = 400)
                        decor5.fadeHide(duration = 400)
                    })

                    decor6.alpha = 1f
                    decor6.yToDp(50f, duration = 300)
                    decor6.width(80, 300, onEnd = {
                        decor6.width(30, 400)
                        decor6.yToDp(70f, duration = 400)
                        decor6.fadeHide(duration = 400)
                    })

                    decor7.alpha = 1f
                    decor7.yToDp(50f, duration = 300)
                    decor7.xToDp(50f, duration = 300)
                    decor7.width(80, 300, onEnd = {
                        decor7.width(30, 400)
                        decor7.yToDp(70f, duration = 400)
                        decor7.xToDp(70f, duration = 400)
                        decor7.fadeHide(duration = 400)
                    })

                    decor8.alpha = 1f
                    decor8.xToDp(50f, duration = 300)
                    decor8.width(80, 300, onEnd = {
                        decor8.width(30, 400)
                        decor8.xToDp(70f, duration = 400)
                        decor8.fadeHide(duration = 400)
                    })

                    circle1.alpha = 1f
                    circle1.yToDp(-140f, percent = 30f, duration = 1000, onPercent = {
                        circle1.fadeHide(duration = 500)
                    })

                    circle2.alpha = 1f
                    circle2.xToDp(-80f, duration = 1000)
                    circle2.yToDp(-40f, percent = 30f, duration = 1000, onPercent = {
                        circle2.fadeHide(duration = 500)
                    })

                    circle3.alpha = 1f
                    circle3.xToDp(-80f, duration = 1000)
                    circle3.yToDp(40f, percent = 30f, duration = 1000, onPercent = {
                        circle3.fadeHide(duration = 500)
                    })

                    circle4.alpha = 1f
                    circle4.xToDp(70f, duration = 1000)
                    circle4.yToDp(70f, percent = 30f, duration = 1000, onPercent = {
                        circle4.fadeHide(duration = 500)
                    })

                    circle5.alpha = 1f
                    circle5.xToDp(70f, duration = 1000)
                    circle5.yToDp(-70f, percent = 30f, duration = 1000, onPercent = {
                        circle5.fadeHide(duration = 500,callback={
                            viewModel.isAnimating = false
                        })
                    })

                    card.scale(0.85f, duration = 700, onEnd = {
                        card.scale(0.8f, duration = 2000)
                    })
                })
            })
        }
    }
}