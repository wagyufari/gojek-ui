package com.mayburger.gojekuiclone.ui.grabpay

import android.os.Bundle
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
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeHide
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeShow
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
            if (state == 1) {
                toMain()
            }
            cardInfo.fadeHide()
            cardLock.fadeHide {
                cardUnlock.fadeShow()
            }
            playLockCardAnimation()
        }

        cardUnlock.setOnClickListener {
            cardUnlock.fadeHide {
                cardInfo.fadeShow()
                cardLock.fadeShow()
            }
            playUnlockCardAnimation()
        }
    }
}