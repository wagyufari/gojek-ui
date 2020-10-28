package com.mayburger.gojekuiclone.ui

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import java.lang.Math.min

class CircleCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : CardView(context, attrs, defStyleAttr) {

    override fun setRadius(radius: Float) {
        super.setRadius(if (radius > height / 2 || radius > width / 2) height.coerceAtMost(width) / 2f
            else radius
        )
    }
}