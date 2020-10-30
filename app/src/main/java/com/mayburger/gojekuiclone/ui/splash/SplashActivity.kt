package com.mayburger.gojekuiclone.ui.splash

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.data.hawk.AppHawkHelper
import com.mayburger.gojekuiclone.ui.main.MainActivity
import com.mayburger.gojekuiclone.util.ext.ViewUtils.isColorDark
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Glide.with(this).asBitmap().load("https://i.ibb.co/GPSRGYn/homebackground.png")
                .into(object : CustomTarget<Bitmap>(1080,1920) {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        CoroutineScope(IO).launch{
                            val theme = if(resource.isColorDark()) R.style.Theme_Gojek_Main_Dark else R.style.Theme_Gojek_Main_Light
                            MainActivity.startActivity(this@SplashActivity,theme)
                            finish()
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }
                })

    }
}