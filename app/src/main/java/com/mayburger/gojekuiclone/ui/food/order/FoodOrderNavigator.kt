package com.mayburger.gojekuiclone.ui.food.order

import androidx.fragment.app.Fragment
import com.mayburger.gojekuiclone.ui.base.BaseNavigator

interface FoodOrderNavigator:BaseNavigator{
    fun onBackPressed(fragment: Fragment)
}