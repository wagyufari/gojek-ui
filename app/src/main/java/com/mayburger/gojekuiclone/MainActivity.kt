package com.mayburger.gojekuiclone

import android.os.Bundle
import androidx.activity.viewModels
import com.mayburger.gojekuiclone.databinding.ActivityMainBinding
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.ui.main.MainNavigator
import com.mayburger.gojekuiclone.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}