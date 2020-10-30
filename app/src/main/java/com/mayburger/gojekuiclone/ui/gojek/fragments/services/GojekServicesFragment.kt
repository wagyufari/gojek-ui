package com.mayburger.gojekuiclone.ui.gojek.fragments.services


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentGojekServicesBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GojekServicesFragment : BaseFragment<FragmentGojekServicesBinding, GojekServicesViewModel>(),
        GojekServicesNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_gojek_services
    override val viewModel: GojekServicesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}