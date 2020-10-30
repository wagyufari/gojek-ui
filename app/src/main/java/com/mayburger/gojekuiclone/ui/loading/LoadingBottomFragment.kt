package com.mayburger.gojekuiclone.ui.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentLoadingBottomBinding
import com.mayburger.gojekuiclone.databinding.FragmentMainHomeBinding
import com.mayburger.gojekuiclone.ui.base.BaseBSDFragment
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import com.mayburger.gojekuiclone.ui.main.fragments.home.MainHomeNavigator
import com.mayburger.gojekuiclone.ui.main.fragments.home.MainHomeViewModel
import com.mayburger.gojekuiclone.util.ext.onTransitionEnd
import com.mayburger.gojekuiclone.util.ext.onTransitionProgress
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_loading_bottom.*

@AndroidEntryPoint
class LoadingBottomFragment : BaseBSDFragment<FragmentLoadingBottomBinding, LoadingBottomViewModel>(),
        LoadingBottomNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_loading_bottom
    override val viewModel: LoadingBottomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        if(motionLayout.currentState == R.id.end){
            motionLayout.transitionToStart()
        }
        motionLayout.onTransitionEnd {
            if (it == R.id.end) {
                motionLayout.transitionToStart()
            } else {
                motionLayout.transitionToEnd()
            }
        }

    }

}