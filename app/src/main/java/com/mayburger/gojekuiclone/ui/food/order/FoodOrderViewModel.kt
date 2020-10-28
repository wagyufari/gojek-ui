package com.mayburger.gojekuiclone.ui.food.order

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimatedVectorDrawable
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.hilt.lifecycle.ViewModelInject
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.data.DataManager
import com.mayburger.gojekuiclone.ui.base.BaseViewModel
import com.mayburger.gojekuiclone.util.rx.SchedulerProvider
import kotlinx.android.synthetic.main.fragment_food_order.*


class FoodOrderViewModel @ViewModelInject constructor(
        dataManager: DataManager,
        schedulerProvider: SchedulerProvider
) :
        BaseViewModel<FoodOrderNavigator>(dataManager, schedulerProvider) {
    override fun onEvent(obj: Any) {
    }


    fun playOrderAnimation(fragment: FoodOrderFragment){
        fragment.motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                if (p3 > 0.8) {
                    AnimatorSet().apply {
                        fragment.image.setImageResource(R.drawable.ic_done)
                        val drawable = fragment.image.drawable
                        if (drawable is AnimatedVectorDrawableCompat) {
                            drawable.start()
                        } else if (drawable is AnimatedVectorDrawable) {
                            drawable.start()
                        }

                        play(ObjectAnimator.ofFloat(fragment.text, View.TRANSLATION_Y, 50f, 0f).apply {
                            duration = 500
                        })
                        play(ObjectAnimator.ofFloat(fragment.text, View.ALPHA, 0f, 1f).apply {
                            duration = 500
                        })

                        play(ObjectAnimator.ofFloat(fragment.text, View.TRANSLATION_Y, 0f, 50f).apply {
                            duration = 500
                        }).after(1800)
                        play(ObjectAnimator.ofFloat(fragment.text, View.ALPHA, 1f, 0f).apply {
                            duration = 500
                        }).after(1800)

                        play(ObjectAnimator.ofFloat(fragment.card, View.TRANSLATION_Y, 0f, 700f).apply {
                            duration = 1300
                            interpolator = PathInterpolatorCompat.create(0.66f, -0.09f, 0f, 1.08f)
                        })
                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_Y, 1f, 0.7f).apply {
                            duration = 100
                        }).after(800)
                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_Y, 0.7f, 1f).apply {
                            duration = 200
                        }).after(1000)

                        play(ObjectAnimator.ofFloat(fragment.card, View.TRANSLATION_Y, 700f, 0f).apply {
                            duration = 1300
                            interpolator = PathInterpolatorCompat.create(.1f, .98f, .99f, 1f)
                        }).after(1000)

                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_X, 1f, 0.2f).apply {
                            duration = 200
                            addListener(object : Animator.AnimatorListener {
                                override fun onAnimationRepeat(p0: Animator?) {}
                                override fun onAnimationEnd(p0: Animator?) {
                                    fragment.card.cardElevation = 2f
                                    fragment.image.setPadding(0, 0, 0, 0)
                                    fragment.image.setImageResource(R.drawable.gofood)
                                }

                                override fun onAnimationCancel(p0: Animator?) {}
                                override fun onAnimationStart(p0: Animator?) {
                                }
                            })
                        }).after(1600)
                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_X, 0.2f, 1f).apply {
                            duration = 200
                        }).after(1800)
                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_X, 1f, 0.6f).apply {
                            duration = 2000
                        }).after(2500)
                        play(ObjectAnimator.ofFloat(fragment.card, View.SCALE_Y, 1f, 0.6f).apply {
                            duration = 2000
                        }).after(2500)
                        play(ObjectAnimator.ofFloat(fragment.card, View.TRANSLATION_Y, 0f, 700f).apply {
                            duration = 2000
                        }).after(2500)

                        play(ObjectAnimator.ofFloat(fragment.background,View.ALPHA, 1f,0f).apply{
                            duration = 1200
                        }).after(2500)

                        play(ObjectAnimator.ofFloat(fragment.marker_foot, View.TRANSLATION_Y, 0f, 630f))
                        play(ObjectAnimator.ofFloat(fragment.marker_foot, View.ALPHA, 0f, 1f).apply {
                            duration = 500
                        }).after(4300)
                        start()
                    }

                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }
        })
    }

}