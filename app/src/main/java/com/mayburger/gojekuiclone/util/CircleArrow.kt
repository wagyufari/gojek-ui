package com.mayburger.gojekuiclone.util

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.mayburger.gojekuiclone.R
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeHide
import com.mayburger.gojekuiclone.util.ext.ViewUtils.fadeShow
import com.mayburger.gojekuiclone.util.ext.ViewUtils.animToX
import com.mayburger.gojekuiclone.util.ext.bindView


class CircleArrow @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {

    private val chevron1: ImageView by bindView(R.id.chevron1)
    private val chevron2: ImageView by bindView(R.id.chevron2)

    init {
        inflate(context, R.layout.circle_arrow, this)
        animateChevrons()
    }

    fun animateChevrons() {
        delay(500){
            chevron2.fadeHide(duration = 500)
            chevron2.animToX(4f, duration = 700)
            delay(200) {
                chevron1.fadeHide(duration = 500)
                chevron1.animToX(4f, duration = 700)
            }
            delay(700) {
                chevron1.x = -2f
                chevron2.x = -2f
                chevron2.fadeShow(duration = 1000)
                chevron2.animToX(0f, duration = 1000)
                delay(100) {
                    chevron1.fadeShow(duration = 1000)
                    chevron1.animToX(0f, duration = 1000)
                    delay(300) {
                        animateChevrons()
                    }
                }
            }
        }
    }

    fun delay(delay: Long, runnable: () -> Unit) {
        Handler().postDelayed({
            runnable.invoke()
        }, delay)
    }

}