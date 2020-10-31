package com.mayburger.gojekuiclone.util.ext

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.animation.addListener
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.palette.graphics.Palette
import com.mayburger.gojekuiclone.R
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object ViewUtils {

    private var screenWidth = 0
    private var screenHeight = 0

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun dpToPxFloat(dp: Float): Float {
        return (dp * Resources.getSystem().displayMetrics.density)
    }

    suspend fun Bitmap.isColorDark(): Boolean {
        return suspendCoroutine { cont ->
            Palette.generateAsync(this) {
                val color = it?.getDominantColor(Color.parseColor("#2B2D2F")) ?: 0
                cont.resume(isColorDark(color))
            }
        }
    }

    fun isColorDark(color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255
        return if (darkness < 0.5) {
            false // It's a light color
        } else {
            true // It's a dark color
        }
    }

    fun getProgressDialog(context: Context, message: CharSequence): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.isIndeterminate = true
        val drawable = ProgressBar(context).indeterminateDrawable.mutate()
        drawable.setColorFilter(
                context.resources.getColor(R.color.purple_200),
                PorterDuff.Mode.SRC_IN
        )
        progressDialog.setIndeterminateDrawable(drawable)
        progressDialog.setCancelable(false)
        return progressDialog
    }

    fun View.flipX(
            duration: Long? = 400,
            onFlip: (() -> Unit)? = {},
            onEnd: (() -> Unit)? = {},
            after: Long? = 0
    ) {
        Handler().postDelayed({
            AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(this@flipX, View.SCALE_X, 1f).apply {
                    this.duration = duration ?: 400
                    addListener(onEnd = {
                        onEnd?.invoke()
                    })
                }).after(
                        ObjectAnimator.ofFloat(this@flipX, View.SCALE_X, 0f).apply {
                            this.duration = duration ?: 400
                            addListener(onEnd = {
                                onFlip?.invoke()
                            })
                        }
                )
                start()
            }
        },after?:0)
    }

    fun View.shake() {
        this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake))
    }

    fun View.animToY(
            y: Float,
            animate: Boolean? = true,
            after: Long? = 0,
            duration: Long? = 500,
            onEnd: (() -> Unit)? = {},
            percent: Float? = 100f,
            onPercent: (() -> Unit)? = {},
            interpolator: TimeInterpolator? = null
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@animToY, View.TRANSLATION_Y, dpToPxFloat(y)).apply {
                this.duration = if (animate != false) duration ?: 500 else 0
                Handler().postDelayed({
                    onPercent?.let {
                        it.invoke()
                        this.cancel()
                    }
                }, ((duration ?: 500).times(percent ?: 100f) / 100).toLong())
                addListener(onEnd = {
                    onEnd?.invoke()
                })
                interpolator?.let {
                    this.interpolator = it
                }
            }).after(after ?: 0)
            start()
        }
    }

    fun View.animToX(
            x: Float,
            animate: Boolean? = true,
            duration: Long? = 500,
            onEnd: (() -> Unit)? = {},
            percent: Float? = 100f,
            onPercent: (() -> Unit)? = {}
    ) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@animToX, View.TRANSLATION_X, dpToPxFloat(x)).apply {
                this.duration = if (animate != false) duration ?: 500 else 0
                Handler().postDelayed({
                    onPercent?.invoke()
                }, ((duration ?: 500).times(percent ?: 100f) / 100).toLong())
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            })
            start()
        }
    }

    fun View.animToAngle(angle:Double, radius:Float,duration: Long? = 500){

    }

    fun View.scale(scale: Float, duration: Long? = 1000, onEnd: (() -> Unit)? = {}, after: Long? = 0) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scale, View.SCALE_X, scale).apply {
                this.duration = duration ?: 1000
            }).after(after ?: 0)
            play(ObjectAnimator.ofFloat(this@scale, View.SCALE_Y, scale).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            }).after(after ?: 0)
            start()
        }
    }


    fun View.scaleY(scale: Float, duration: Long? = 1000, onEnd: (() -> Unit)? = {}, after: Long? = 0) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scaleY, View.SCALE_Y, scale).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            }).after(after ?: 0)
            start()
        }
    }

    fun View.scaleX(scale: Float, duration: Long? = 1000, onEnd: (() -> Unit)? = {}) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@scaleX, View.SCALE_X, scale).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            })
            start()
        }
    }

    fun View.width(
            width: Int, duration: Long? = 500, onEnd: (() -> Unit)? = {}, percent: Float? = 100f,
            onPercent: (() -> Unit)? = {}
    ) {
        ValueAnimator.ofInt(this.width, dpToPx(width)).apply {
            this.duration = duration ?: 500
            Handler().postDelayed({
                onPercent?.invoke()
            }, (500.times(percent ?: 100f) / 100).toLong())
            addUpdateListener {
                this@width.layoutParams.width = it.animatedValue as Int
                this@width.requestLayout()
            }
            addListener(onEnd = {
                onEnd?.invoke()
            })
            start()
        }
    }

    fun View.shrinkHide(callback: (() -> Unit)? = {}) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@shrinkHide, View.SCALE_X, 0f).apply {
                duration = 300
            })
            play(ObjectAnimator.ofFloat(this@shrinkHide, View.SCALE_Y, 0f).apply {
                duration = 300
                addListener(onEnd = {
                    callback?.invoke()
                    this@shrinkHide.visibility = View.GONE
                })
            })
            start()
        }
    }

    fun View.expandShow(callback: (() -> Unit)? = {}) {
        this.visibility = View.VISIBLE
        this.scaleX = 0f
        this.alpha = 1f
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@expandShow, View.SCALE_X, 1f).apply {
                duration = 300
            })
            play(ObjectAnimator.ofFloat(this@expandShow, View.SCALE_Y, 1f).apply {
                duration = 300
                addListener(onEnd = {
                    callback?.invoke()
                })
            })
            start()
        }
    }


    fun View.fadeHide(onEnd: (() -> Unit)? = { }, duration: Long? = 700, after: Long? = 0) {
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@fadeHide, View.ALPHA, 0f).apply {
                this.duration = duration ?: 700
                addListener(onEnd = {
                    onEnd?.invoke()
                    this@fadeHide.visibility = View.GONE
                })
            }).after(after ?: 0)
            start()
        }
    }

    fun View.fadeShow(onEnd: (() -> Unit)? = {}, duration: Long? = 1000, after: Long? = 0) {
        this.alpha = 0f
        this.visibility = View.VISIBLE
        AnimatorSet().apply {
            play(ObjectAnimator.ofFloat(this@fadeShow, View.ALPHA, 1f).apply {
                this.duration = duration ?: 1000
                addListener(onEnd = {
                    onEnd?.invoke()
                })
            }).after(after ?: 0)
            start()
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
                activity.findViewById<View>(R.id.content).windowToken,
                0
        )
    }

    fun showKeyboard(view: View) {
        val imm = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }


    fun getScreenWidth(context: Context): Int {
        if (screenWidth == 0) {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            screenWidth = size.x
        }
        return screenWidth
    }

}

fun Drawable.toBitmap(context: Context): Bitmap {
    val drawable = this
    var bitmap: Bitmap? = null

    if (drawable is BitmapDrawable) {
        val bitmapDrawable = drawable as BitmapDrawable
        if (bitmapDrawable.bitmap != null) {
            return bitmapDrawable.bitmap
        }
    }

    bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
        Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
        ) // Single color bitmap will be created of 1x1 pixel
    } else {
        Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
        )
    }

    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

fun TextView.setReadMore(
        rootNotes: View,
        showReadMore: ObservableBoolean,
        maxLine: ObservableField<Int>
) {
    this.post {
        showReadMore.set(this.lineCount > 4)
        if (showReadMore.get()) {
            maxLine.set(4)
            rootNotes.setOnClickListener {
                if (maxLine.get() == 4) {
                    maxLine.set(Int.MAX_VALUE)
                    showReadMore.set(false)
                } else {
                    maxLine.set(4)
                    showReadMore.set(true)
                }
            }
        }
    }
}

fun Bitmap.getRoundedCornerBitmap(pixels: Int): Bitmap? {
    val bitmap = this
    val output = Bitmap.createBitmap(
            bitmap.width, bitmap
            .height, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(output)
    val color = -0xbdbdbe
    val paint = Paint()
    val rect = Rect(0, 0, bitmap.width, bitmap.height)
    val rectF = RectF(rect)
    val roundPx = pixels.toFloat()
    paint.isAntiAlias = true
    canvas.drawARGB(0, 0, 0, 0)
    paint.color = color
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint)
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(bitmap, rect, rect, paint)
    return output
}


fun View.hideKeyboard() {
    val imm = this.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(
            (this.context as Activity).findViewById<View>(R.id.content).windowToken,
            0
    )
}

fun View.showKeyboard() {
    val imm = this.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, 0)
}

