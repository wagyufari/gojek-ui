package com.mayburger.gojekuiclone.ui.grabpay

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.animation.addListener
import com.mayburger.gojekuiclone.ui.base.BaseViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeShow
import com.mayburger.gojekuiclone.util.ext.ViewUtils.flipX
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import kotlinx.android.synthetic.main.activity_grab_pay.*


class GrabPayViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<GrabPayNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }


    companion object {

        fun playLines(drawable: Drawable) {
            if (drawable is AnimatedVectorDrawableCompat) {
                drawable.start()
            } else if (drawable is AnimatedVectorDrawable) {
                drawable.start()
            }
        }

        fun GrabPayActivity.playLockCardAnimation() {
            val animation = ValueAnimator.ofFloat(1f, 0f)
            animation.duration = 1000
            animation.addUpdateListener {
                val cm = ColorMatrix()
                cm.setSaturation(animation.animatedValue as Float)
                val greyscalePaint = Paint()
                greyscalePaint.colorFilter = ColorMatrixColorFilter(cm)
                card.setLayerType(View.LAYER_TYPE_HARDWARE, greyscalePaint);
            }
            animation.start()
        }

        fun GrabPayActivity.playUnlockCardAnimation() {
            val animation = ValueAnimator.ofFloat(0f, 1f)
            animation.duration = 1000
            animation.addUpdateListener {
                val cm = ColorMatrix()
                cm.setSaturation(animation.animatedValue as Float)
                val greyscalePaint = Paint()
                greyscalePaint.colorFilter = ColorMatrixColorFilter(cm)
                card.setLayerType(View.LAYER_TYPE_HARDWARE, greyscalePaint);
            }
            animation.start()
        }

        fun GrabPayActivity.toCardDetail() {
            isAnimating = true
            card.flipX(onFlip = {
                image.visibility = View.INVISIBLE
                image2.visibility = View.INVISIBLE
                cardDetail.fadeShow {
                    isAnimating = false
                }
            })
            state = 1
        }

        fun GrabPayActivity.toMain(onEnd:(()->Unit)?={}) {
            isAnimating = true
            AnimatorSet().apply {
                card.flipX(onFlip = {
                    cardDetail.alpha = 0f
                }, onEnd = {
                    image2.visibility = View.VISIBLE
                    playLines(image2.drawable)
                    image.visibility = View.VISIBLE
                    playLines(image.drawable)
                    onEnd?.invoke()
                })
                delay(1000) {
                    isAnimating = false
                }
                start()
            }
            state = 0
        }

        fun GrabPayActivity.playInitialAnimation() {
            if(viewModel.dataManager.isGrabCardLocked){
                playLockCardAnimation()
            }
            delay(300) {
                image2.visibility = View.VISIBLE
                playLines(image2.drawable)
                image.visibility = View.VISIBLE
                playLines(image.drawable)
                delay(500) {
                    mastercard.fadeShow()
                    logo.fadeShow {
                        isAnimating = false
                    }
                }
            }
        }
    }

}