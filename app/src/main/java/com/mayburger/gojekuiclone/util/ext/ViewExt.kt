package com.mayburger.gojekuiclone.util.ext

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mayburger.gojekuiclone.R


object ViewUtils {

    private var screenWidth = 0
    private var screenHeight = 0

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getProgressDialog(context: Context, message: CharSequence): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage(message)
        progressDialog.isIndeterminate = true
        val drawable = ProgressBar(context).indeterminateDrawable.mutate()
        drawable.setColorFilter(context.resources.getColor(R.color.purple_200), PorterDuff.Mode.SRC_IN)
        progressDialog.setIndeterminateDrawable(drawable)
        progressDialog.setCancelable(false)
        return progressDialog
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

fun Drawable.toBitmap(context:Context):Bitmap{
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

fun TextView.setReadMore(rootNotes:View, showReadMore:ObservableBoolean, maxLine:ObservableField<Int>){
    this.post {
        showReadMore.set(this.lineCount > 4)
        if (showReadMore.get()){
            maxLine.set(4)
            rootNotes.setOnClickListener {
                if (maxLine.get() == 4){
                    maxLine.set(Int.MAX_VALUE)
                    showReadMore.set(false)
                } else{
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

