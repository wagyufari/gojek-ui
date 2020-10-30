package com.mayburger.gojekuiclone.ui.loading

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentLoadingBottomBinding
import com.mayburger.gojekuiclone.ui.base.BaseBSDFragment
import com.mayburger.gojekuiclone.util.ext.addTransitionListener
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

    var isAnimating = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        motionLayout.addTransitionListener(
                onStarted = { p0, p1, p2 ->
                    isAnimating = true
                },
                onEnd = { p0: MotionLayout?, p1: Int ->
                    if (p1 == R.id.end) {
                        motionLayout.transitionToState(R.id.start)
                    } else {
                        motionLayout.transitionToState(R.id.end)
                    }
                })

    }

}