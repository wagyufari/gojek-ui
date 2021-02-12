package com.mayburger.gojekuiclone.util

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeHide
import java.lang.Math.cos
import java.lang.Math.sin

class FireworkView @JvmOverloads constructor(context: Context,
                                             val attrs: AttributeSet? = null, val defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    var circles = 12
    private var color = Color.RED

    fun setColor(color:Int){
        this.color = color
    }

    fun setColor(color:String){
        this.color = Color.parseColor(color)
    }

    fun start(){
        val firework1 = Firework(context,attrs,defStyleAttr)
        val firework2 = Firework(context,attrs,defStyleAttr)
        addView(firework1)
        addView(firework2)
        firework1.startAnimation(circles)
        firework1.fadeHide(duration=2000)
        firework1.color = this.color
        Handler().postDelayed({
            firework2.startAnimation(circles)
            firework2.fadeHide(duration=2000)
            firework2.color= this@FireworkView.color
        },200)
    }


}

class Firework @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    var radius = 0f
    var color = Color.RED
    var animator:ValueAnimator?=null

    init {
        animator = ValueAnimator()
        animator?.duration = 2000
        animator?.addUpdateListener {
            radius = it.animatedValue as Float
            this.invalidate()
        }
    }

    val angles = ArrayList<Int>()

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun startAnimation(circles:Int){
        for(i in 1..circles){
            angles.add((360/circles)*i)
        }
        animator?.setFloatValues(0f,dpToPx(100).toFloat())
        animator?.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        angles.forEach {
            val x = (cos(Math.toRadians((it.toDouble()))).toFloat()) * radius
            val y = (sin(Math.toRadians((it.toDouble()))).toFloat()) * radius
            canvas?.let { it1 -> drawAllCircle(it1, (width/2)+x,(height/2)+y) }
        }
    }

    fun drawAllCircle(canvas:Canvas, x:Float, y:Float){
        canvas.drawCircle(x, y, 15f, Paint().apply {
            isAntiAlias = true
            color = this@Firework.color
            style = Paint.Style.FILL
        })
    }

}