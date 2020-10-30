package com.mayburger.gojekuiclone.ui.main.fragments.home


import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentMainHomeBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import com.mayburger.gojekuiclone.ui.loading.LoadingBottomFragment
import com.mayburger.gojekuiclone.ui.pay.review.PayReviewFragment
import com.mayburger.gojekuiclone.ui.pay.success.PaySuccessFragment
import com.mayburger.gojekuiclone.util.ext.sheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_home.*

@AndroidEntryPoint
class MainHomeFragment : BaseFragment<FragmentMainHomeBinding, MainHomeViewModel>(),
        MainHomeNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_main_home
    override val viewModel: MainHomeViewModel by viewModels()
    lateinit var behavior: BottomSheetBehavior<*>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        behavior = bottomSheet.sheetBehavior()
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        behavior.peekHeight = height / 2
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onClickPay() {
        LoadingBottomFragment().apply {
            show(this@MainHomeFragment.requireActivity().supportFragmentManager, "")
            delay(1000) {
                if (isAnimating){
                    this.dismiss()
                    PayReviewFragment().apply {
                        onPay = {
                            dismiss()
                            this@MainHomeFragment.requireActivity().supportFragmentManager.beginTransaction().apply {
                                add(R.id.mainContainer, PaySuccessFragment(), "")
                                commit()
                            }
                        }
                        show(this@MainHomeFragment.requireActivity().supportFragmentManager, "")
                    }
                }
            }
        }
    }
}