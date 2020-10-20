package com.mayburger.gojekuiclone.util.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter


object ImageViewBinding {

    @BindingAdapter("imageResource")
    @JvmStatic
    fun setImageResource(view: ImageView, src:Int){
        view.setImageResource(src)
    }
}