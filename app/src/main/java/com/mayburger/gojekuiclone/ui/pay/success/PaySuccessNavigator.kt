package com.mayburger.gojekuiclone.ui.pay.success

import androidx.fragment.app.Fragment
import com.mayburger.gojekuiclone.ui.base.BaseNavigator

interface PaySuccessNavigator:BaseNavigator{
    fun onBackPressed(fragment:Fragment)
}