package com.mayburger.gojekuiclone.ui.food.gopay

import androidx.fragment.app.Fragment
import com.mayburger.gojekuiclone.ui.base.BaseNavigator

interface GopaySuccessNavigator:BaseNavigator{
    fun onBackPressed(fragment:Fragment)
}