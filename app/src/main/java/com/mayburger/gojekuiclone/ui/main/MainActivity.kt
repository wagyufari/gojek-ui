package com.mayburger.gojekuiclone.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityMainBinding
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {

//    override val bindingVariable: Int
//        get() = BR.viewModel
//    override val layoutId: Int
//        get() = R.layout.activity_main
//    override val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}