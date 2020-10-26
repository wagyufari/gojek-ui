package com.mayburger.gojekuiclone.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.view.View

class DemoView(context: Context?) : View(context) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val p = Paint()
        p.color = Color.RED
        val dashPath = DashPathEffect(floatArrayOf(5f, 5f), 1.0.toFloat())
        p.pathEffect = dashPath
        p.style = Paint.Style.STROKE
        canvas.drawCircle(100f, 100f, 50f, p)
        invalidate()
    }
}