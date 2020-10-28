package com.mayburger.gojekuiclone.ui.food.order

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.FragmentFoodOrderBinding
import com.mayburger.gojekuiclone.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_food_order.*


@AndroidEntryPoint
class FoodOrderFragment : BaseFragment<FragmentFoodOrderBinding, FoodOrderViewModel>(), FoodOrderNavigator {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_food_order
    override val viewModel: FoodOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        card.setOnClickListener {
            if (motionLayout.currentState == R.id.start) {
                textOrder.visibility = View.GONE
                progressOrder.visibility = View.VISIBLE
                delay(2000) {
                    progressOrder.visibility = View.GONE
                    motionLayout.transitionToEnd()
                }
                viewModel.playOrderAnimation(this@FoodOrderFragment)
            }
        }
    }

}