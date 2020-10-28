package com.mayburger.gojekuiclone.ui.grabpay

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityGrabPayBinding
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.util.ext.onTransitionEnd
import com.mayburger.gojekuiclone.util.ext.onTransitionProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_grab_pay.*

@AndroidEntryPoint
class GrabPayActivity : BaseActivity<ActivityGrabPayBinding, GrabPayViewModel>(), GrabPayNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_grab_pay
    override val viewModel: GrabPayViewModel by viewModels()

    var state = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        card.setOnClickListener {
            if (state == 0) {
                toCardDetail()
            } else {
                toMain()
            }
        }

        delay(300) {
            image2.visibility = View.VISIBLE
            playLines(image2.drawable)
            image.visibility = View.VISIBLE
            playLines(image.drawable)
            AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(mastercard, View.ALPHA, 1f).apply {
                    duration = 1000
                }).after(1000)
                play(ObjectAnimator.ofFloat(logo, View.ALPHA, 1f).apply {
                    duration = 1000
                }).after(1000)
                start()
            }
        }
    }

    fun toCardDetail() {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(card, View.SCALE_X, 0.1f).apply {
                duration = 200
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        image.visibility = View.INVISIBLE
                        image2.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationStart(p0: Animator?) {
                    }
                })
            })
            play(ObjectAnimator.ofFloat(card, View.SCALE_X, 1f).apply {
                duration = 200
            }).after(200)
            play(ObjectAnimator.ofFloat(cardDetails, View.ALPHA, 1f).apply {
                duration = 700
            }).after(600)
            start()
        }
        state = 1
    }

    fun toMain() {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(card, View.SCALE_X, 0.1f).apply {
                duration = 200
                addListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(p0: Animator?) {
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        cardDetails.alpha = 0f
                    }

                    override fun onAnimationCancel(p0: Animator?) {
                    }

                    override fun onAnimationStart(p0: Animator?) {
                    }
                })
            })
            play(ObjectAnimator.ofFloat(card, View.SCALE_X, 1f).apply {
                duration = 200
            }).after(200)
            delay(200) {
                image2.visibility = View.VISIBLE
                playLines(image2.drawable)
                image.visibility = View.VISIBLE
                playLines(image.drawable)
            }
            start()
        }
        state = 0
    }

    fun playLines(drawable: Drawable) {
        if (drawable is AnimatedVectorDrawableCompat) {
            drawable.start()
        } else if (drawable is AnimatedVectorDrawable) {
            drawable.start()
        }
    }
}