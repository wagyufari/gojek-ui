package com.mayburger.gojekuiclone.ui.grabpay

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityGrabPayBinding
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.ui.grabpay.GrabPayViewModel.Companion.playInitialAnimation
import com.mayburger.gojekuiclone.ui.grabpay.GrabPayViewModel.Companion.playLockCardAnimation
import com.mayburger.gojekuiclone.ui.grabpay.GrabPayViewModel.Companion.playUnlockCardAnimation
import com.mayburger.gojekuiclone.ui.grabpay.GrabPayViewModel.Companion.toCardDetail
import com.mayburger.gojekuiclone.ui.grabpay.GrabPayViewModel.Companion.toMain
import com.mayburger.gojekuiclone.util.ext.ViewUtils.expandShow
import com.mayburger.gojekuiclone.util.ext.ViewUtils.shake
import com.mayburger.gojekuiclone.util.ext.ViewUtils.shrinkHide
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
    var isAnimating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playInitialAnimation()
        cardInfo.setOnClickListener {
            if (!isAnimating) {
                if (state == 0) {
                    toCardDetail()
                } else {
                    toMain()
                }
            }
        }
        cardLock.setOnClickListener {
            viewModel.dataManager.isGrabCardLocked = true
            if (state == 1) {
                toMain {
                    textLocked.expandShow()
                }
            } else {
                textLocked.expandShow()
            }
            cardInfo.shrinkHide()
            cardLock.shrinkHide {
                cardUnlock.expandShow()
            }
            card.shake()
            playLockCardAnimation()
        }

        cardUnlock.setOnClickListener {
            viewModel.dataManager.isGrabCardLocked = false
            cardUnlock.shrinkHide {
                cardInfo.expandShow()
                cardLock.expandShow()
            }
            textLocked.shrinkHide()
            playUnlockCardAnimation()
        }
    }
}