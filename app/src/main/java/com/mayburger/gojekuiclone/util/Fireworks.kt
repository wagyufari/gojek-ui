package com.mayburger.gojekuiclone.util

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.util.ext.ViewUtils.dpToPx
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeHide
import com.mayburger.gojekuiclone.util.ext.ViewUtils.animToX
import com.mayburger.gojekuiclone.util.ext.ViewUtils.animToY
import com.mayburger.gojekuiclone.util.ext.bindView
import kotlin.math.cos
import kotlin.math.sin

class Fireworks @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    val firework0:View by bindView(R.id.firework0)
    val firework1:View by bindView(R.id.firework1)
    val firework2:View by bindView(R.id.firework2)
    val firework3:View by bindView(R.id.firework3)
    val firework4:View by bindView(R.id.firework4)
    val firework5:View by bindView(R.id.firework5)
    val firework6:View by bindView(R.id.firework6)
    val firework7:View by bindView(R.id.firework7)
    val firework8:View by bindView(R.id.firework8)
    val firework9:View by bindView(R.id.firework9)
    val firework10:View by bindView(R.id.firework10)
    val firework11:View by bindView(R.id.firework11)
    val firework12:View by bindView(R.id.firework12)
    val firework13:View by bindView(R.id.firework13)
    val firework14:View by bindView(R.id.firework14)
    val firework15:View by bindView(R.id.firework15)
    val firework21:View by bindView(R.id.firework21)
    val firework22:View by bindView(R.id.firework22)
    val firework23:View by bindView(R.id.firework23)
    val firework24:View by bindView(R.id.firework24)
    val firework25:View by bindView(R.id.firework25)
    val firework26:View by bindView(R.id.firework26)
    val firework27:View by bindView(R.id.firework27)
    val firework28:View by bindView(R.id.firework28)
    val firework29:View by bindView(R.id.firework29)
    val firework210:View by bindView(R.id.firework210)
    val firework211:View by bindView(R.id.firework211)
    val firework212:View by bindView(R.id.firework212)
    val firework213:View by bindView(R.id.firework213)
    val firework214:View by bindView(R.id.firework214)
    val firework215:View by bindView(R.id.firework215)

    init {
        inflate(context, R.layout.fireworks, this)
    }

    fun setFireworkResource(resource:Int){
        firework0.setBackgroundResource(resource)
        firework1.setBackgroundResource(resource)
        firework2.setBackgroundResource(resource)
        firework3.setBackgroundResource(resource)
        firework4.setBackgroundResource(resource)
        firework5.setBackgroundResource(resource)
        firework6.setBackgroundResource(resource)
        firework7.setBackgroundResource(resource)
        firework8.setBackgroundResource(resource)
        firework9.setBackgroundResource(resource)
        firework10.setBackgroundResource(resource)
        firework11.setBackgroundResource(resource)
        firework12.setBackgroundResource(resource)
        firework13.setBackgroundResource(resource)
        firework14.setBackgroundResource(resource)
        firework15.setBackgroundResource(resource)
        firework21.setBackgroundResource(resource)
        firework22.setBackgroundResource(resource)
        firework23.setBackgroundResource(resource)
        firework24.setBackgroundResource(resource)
        firework25.setBackgroundResource(resource)
        firework26.setBackgroundResource(resource)
        firework27.setBackgroundResource(resource)
        firework28.setBackgroundResource(resource)
        firework29.setBackgroundResource(resource)
        firework210.setBackgroundResource(resource)
        firework211.setBackgroundResource(resource)
        firework212.setBackgroundResource(resource)
        firework213.setBackgroundResource(resource)
        firework214.setBackgroundResource(resource)
        firework215.setBackgroundResource(resource)
    }

    fun start(){
        animate(firework0,0.0)
        animate(firework1,24.0)
        animate(firework2,48.0)
        animate(firework3,72.0)
        animate(firework4,96.0)
        animate(firework5,120.0)
        animate(firework6,144.0)
        animate(firework7,168.0)
        animate(firework8,216.0)
        animate(firework9,240.0)
        animate(firework10,264.0)
        animate(firework11,288.0)
        animate(firework12,312.0)
        animate(firework13,336.0)
        animate(firework14,360.0)
        animate(firework15,192.0)
        delay(200){
            animate(firework21,24.0)
            animate(firework22,48.0)
            animate(firework23,72.0)
            animate(firework24,96.0)
            animate(firework25,120.0)
            animate(firework26,144.0)
            animate(firework27,168.0)
            animate(firework28,216.0)
            animate(firework29,240.0)
            animate(firework210,264.0)
            animate(firework211,288.0)
            animate(firework212,312.0)
            animate(firework213,336.0)
            animate(firework214,360.0)
            animate(firework215,192.0)
        }
    }

    fun animate(view:View, angle:Double) {
        view.alpha = 1f
        view.animToX((cos(Math.toRadians((angle))).toFloat()) * 70, duration = 2000)
        view.animToY((sin(Math.toRadians((angle))).toFloat()) * 70, duration = 2000)
        view.fadeHide(duration = 2000)
    }

    fun delay(delay: Long, runnable: () -> Unit) {
        Handler().postDelayed({
            runnable.invoke()
        }, delay)
    }

    private fun addCircle(angle: Int) {
        addView(View(context).apply {
            tag = angle.toDouble()
            setBackgroundResource(R.drawable.shp_circle_green700)
            val params = LayoutParams(dpToPx(8), dpToPx(8))
            params.addRule(CENTER_IN_PARENT)
            layoutParams = params
        })
    }

}