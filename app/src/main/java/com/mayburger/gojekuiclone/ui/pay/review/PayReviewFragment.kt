package com.mayburger.gojekuiclone.ui.pay.review

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentLoadingBottomBinding
import com.mayburger.gojekuiclone.databinding.FragmentMainHomeBinding
import com.mayburger.gojekuiclone.databinding.FragmentPayReviewBinding
import com.mayburger.gojekuiclone.ui.base.BaseBSDFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayReviewFragment : BaseBSDFragment<FragmentPayReviewBinding, PayReviewViewModel>(),
        PayReviewNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_pay_review
    override val viewModel: PayReviewViewModel by viewModels()

    lateinit var onPay: () -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog =
                super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                    d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            val sheet = BottomSheetBehavior.from<FrameLayout?>(bottomSheet!!)
            sheet.state = BottomSheetBehavior.STATE_EXPANDED
            sheet.skipCollapsed = true
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
    }

    override fun onClickPay() {
        viewModel.isLoading.set(true)
        delay(100) {
            onPay.invoke()
        }
    }
}