package com.mayburger.gojekuiclone.ui.main.fragments.services


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentMainServicesBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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