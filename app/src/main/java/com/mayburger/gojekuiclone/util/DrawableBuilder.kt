package com.mayburger.gojekuiclone.util

import android.graphics.Color
import android.graphics.drawable.GradientDrawable

class DrawableBuilder {

    private var drawable: GradientDrawable = GradientDrawable()

    fun setCornerRadius(radius:Float):DrawableBuilder{
        drawable.cornerRadius = radius
        return this
    }

    fun setColor(color:Int):DrawableBuilder{
        drawable.setColor(color)
        return this
    }

    fun setColor(color:String):DrawableBuilder{
        drawable.setColor(Color.parseColor(color))
        return this
    }

    fun setStroke(color:String, width:Int=1):DrawableBuilder{
        drawable.setStroke(width,Color.parseColor(color))
        return this
    }

    fun setStroke(color:Int, width:Int=1):DrawableBuilder{
        drawable.setStroke(width,color)
        return this
    }

    fun build():GradientDrawable{
        return drawable
    }
}