package com.mayburger.gojekuiclone.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.content.res.Resources
import android.view.View

object AnimationExt{

    fun dpToPx(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

    fun View.translationY(y:Float, duration:Long?=500, interpolator:TimeInterpolator?=null):ObjectAnimator{
        return ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, dpToPx(y)).apply {
            setDuration(duration?:500)
            setInterpolator(interpolator)
        }
    }
    fun View.scaleY(scaleY:Float, duration:Long?=500, interpolator:TimeInterpolator?=null):ObjectAnimator{
        return ObjectAnimator.ofFloat(this, View.SCALE_Y, scaleY).apply {
            setDuration(duration?:500)
            setInterpolator(interpolator)
        }
    }

    fun View.scaleX(){

    }

    fun View.scale(scale:Float, duration:Long?=500, interpolator:TimeInterpolator?=null):ObjectAnimator{
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y,scale)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X,scale)
        return ObjectAnimator.ofPropertyValuesHolder(this, scaleX,scaleY).apply {
            setDuration(duration?:500)
            setInterpolator(interpolator)
        }
    }
}