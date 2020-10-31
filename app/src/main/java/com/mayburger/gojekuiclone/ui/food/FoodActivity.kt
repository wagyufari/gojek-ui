package com.mayburger.gojekuiclone.ui.food

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.mayburger.gojekuiclone.BR
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.databinding.ActivityFoodBinding
import com.mayburger.gojekuiclone.models.events.BackEvent
import com.mayburger.gojekuiclone.ui.base.BaseActivity
import com.mayburger.gojekuiclone.ui.food.order.FoodOrderFragment
import com.mayburger.gojekuiclone.ui.main.MainActivity
import com.mayburger.gojekuiclone.ui.pay.success.PaySuccessFragment
import com.mayburger.gojekuiclone.util.rx.RxBus
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FoodActivity : BaseActivity<ActivityFoodBinding, FoodViewModel>(), FoodNavigator, OnMapReadyCallback {

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_food
    override val viewModel: FoodViewModel by viewModels()

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Gojek_Main_Light);
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // LE COMPLEX ANIMATION ALGORITHMS

        supportFragmentManager.apply {
            beginTransaction().apply {
                replace(R.id.favoriteContainer, FoodOrderFragment(), "")
                commit()
            }
        }

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.filterIsInstance<FoodOrderFragment>().isNotEmpty()) {
            RxBus.getDefault().send(BackEvent(supportFragmentManager.fragments.filterIsInstance<FoodOrderFragment>()[0]))
        } else {
            super.onBackPressed()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val sydney = LatLng(-6.540667,107.446274)
        val cameraPosition = CameraPosition.Builder()
                .target(sydney)
                .zoom(17f)
                .build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

}