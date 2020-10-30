package com.mayburger.gojekuiclone.ui.food.gopay

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentFoodOrderBinding
import com.mayburger.gojekuiclone.databinding.FragmentGopaySuccessBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import com.mayburger.gojekuiclone.ui.food.gopay.GopaySuccessViewModel.Companion.playSuccessAnimation
import com.mayburger.gojekuiclone.ui.food.order.FoodOrderNavigator
import com.mayburger.gojekuiclone.ui.food.order.FoodOrderViewModel
import com.mayburger.gojekuiclone.util.ext.onTransitionEnd
import com.mayburger.gojekuiclone.util.ext.onTransitionProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_food_order.*


@AndroidEntryPoint
class GopaySuccessFragment : BaseFragment<FragmentGopaySuccessBinding, GopaySuccessViewModel>(),
    GopaySuccessNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_gopay_success
    override val viewModel: GopaySuccessViewModel by viewModels()

    var isAnimating = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        motionLayout.onTransitionProgress {
            if (it > 0.7 && !isAnimating) {
                isAnimating = true
                playSuccessAnimation()
            }
        }
    }

    override fun onBackPressed(fragment: Fragment) {
        motionLayout.transitionToState(R.id.finish)
        motionLayout.onTransitionEnd {
            if (it == R.id.finish) {
                if (!viewModel.isAnimating) {
                    requireActivity().supportFragmentManager.beginTransaction().remove(fragment)
                        .commit()
                }
            }
        }
    }

}