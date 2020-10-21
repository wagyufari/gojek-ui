package com.mayburger.gojekuiclone.ui.main.fragments.services


import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentMainPromosBinding
import com.mayburger.gojekuiclone.databinding.FragmentMainServicesBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import com.mayburger.gojekuiclone.ui.main.fragments.promos.MainPromosNavigator
import com.mayburger.gojekuiclone.ui.main.fragments.promos.MainPromosViewModel
import com.mayburger.gojekuiclone.util.ext.sheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main_promos.*

@AndroidEntryPoint
class MainServicesFragment : BaseFragment<FragmentMainServicesBinding, MainServicesViewModel>(),
        MainServicesNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_main_services
    override val viewModel: MainServicesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}