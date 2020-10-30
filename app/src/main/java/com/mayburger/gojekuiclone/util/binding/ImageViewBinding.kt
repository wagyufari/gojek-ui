package com.mayburger.gojekuiclone.util.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


object ImageViewBinding {

    @BindingAdapter(requireAll = false,value = ["imageResource","imageUrl","imageBitmap"])
    @JvmStatic
    fun setImageResource(view: ImageView, src:Int?,url:String?,bitmap:Bitmap?){
        src?.let{
        view.setImageResource(it)
        }
        bitmap?.let{
            view.setImageBitmap(bitmap)
        }
        url?.let{
            Glide.with(view.context).load(it)
                    .apply(RequestOptions.centerCropTransform())
                    .transition(DrawableTransitionOptions().crossFade())
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(view)
        }
    }
}