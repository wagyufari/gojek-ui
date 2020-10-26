package com.mayburger.gojekuiclone.util.ext

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*


fun Int.toStringJson(mContext: Context): String {
    val `is`: InputStream = mContext.resources.openRawResource(this)
    val writer: Writer = StringWriter()
    val buffer = CharArray(1024)
    `is`.use { `is` ->
        val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
        var n: Int
        while (reader.read(buffer).also { n = it } != -1) {
            writer.write(buffer, 0, n)
        }
    }
    return writer.toString()
}

fun ViewGroup.sheetBehavior():BottomSheetBehavior<*>{
    return BottomSheetBehavior.from(this)
}

fun MotionLayout.onTransitionEnd(trigger:(state:Int)->Unit){
    this.addTransitionListener(object:MotionLayout.TransitionListener{
        override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
        }
        override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
        }
        override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
        }
        override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            trigger.invoke(p1)
        }
    })
}


fun View.setOnClickAnimate(drawable: Drawable?,runnable:Runnable){
    val view = this
    val initial = view.background
    view.setOnTouchListener { p0, p1 ->
        when (p1?.action) {
            MotionEvent.ACTION_DOWN -> {
                val scaleDownX = ObjectAnimator.ofFloat(
                    view,
                    "scaleX", 0.9f
                );
                val scaleDownY = ObjectAnimator.ofFloat(
                    view,
                    "scaleY", 0.9f
                );
                scaleDownX.duration = 150;
                scaleDownY.duration = 150;
                val scaleDown = AnimatorSet();
                scaleDown.play(scaleDownX).with(scaleDownY);
                scaleDown.start();
                drawable?.let{
                    view.background = it
                }
            }
            MotionEvent.ACTION_UP -> {
                val scaleDownX2 = ObjectAnimator.ofFloat(
                    view, "scaleX", 1f
                );
                val scaleDownY2 = ObjectAnimator.ofFloat(
                    view, "scaleY", 1f
                );
                scaleDownX2.duration = 300;
                scaleDownY2.duration = 300;

                val scaleDown2 = AnimatorSet();
                scaleDown2.play(scaleDownX2).with(scaleDownY2);
                scaleDown2.start();
                runnable.run()
                view.performClick()
                drawable?.let{
                    view.background = initial
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                val scaleDownX2 = ObjectAnimator.ofFloat(
                    view, "scaleX", 1f
                );
                val scaleDownY2 = ObjectAnimator.ofFloat(
                    view, "scaleY", 1f
                );
                scaleDownX2.duration = 300;
                scaleDownY2.duration = 300;

                val scaleDown2 = AnimatorSet();
                scaleDown2.play(scaleDownX2).with(scaleDownY2);
                scaleDown2.start();
                drawable?.let{
                    view.background = initial
                }
            }
        }
        true
    }
}
fun View.setOnClickAnimate(drawable:Drawable){
    val view = this
    val initial = view.background
    view.setOnTouchListener { p0, p1 ->
        when (p1?.action) {
            MotionEvent.ACTION_DOWN -> {
                val scaleDownX = ObjectAnimator.ofFloat(
                    view,
                    "scaleX", 0.9f
                );
                val scaleDownY = ObjectAnimator.ofFloat(
                    view,
                    "scaleY", 0.9f
                );
                scaleDownX.duration = 150;
                scaleDownY.duration = 150;
                val scaleDown = AnimatorSet();
                scaleDown.play(scaleDownX).with(scaleDownY);
                scaleDown.start();
                view.background = drawable
            }
            MotionEvent.ACTION_UP -> {
                val scaleDownX2 = ObjectAnimator.ofFloat(
                    view, "scaleX", 1f
                );
                val scaleDownY2 = ObjectAnimator.ofFloat(
                    view, "scaleY", 1f
                );
                scaleDownX2.duration = 300;
                scaleDownY2.duration = 300;

                val scaleDown2 = AnimatorSet();
                scaleDown2.play(scaleDownX2).with(scaleDownY2);
                scaleDown2.start();
                view.performClick()
                view.background = initial
            }
            MotionEvent.ACTION_CANCEL -> {
                val scaleDownX2 = ObjectAnimator.ofFloat(
                    view, "scaleX", 1f
                );
                val scaleDownY2 = ObjectAnimator.ofFloat(
                    view, "scaleY", 1f
                );
                scaleDownX2.duration = 300;
                scaleDownY2.duration = 300;

                val scaleDown2 = AnimatorSet();
                scaleDown2.play(scaleDownX2).with(scaleDownY2);
                scaleDown2.start();
                view.background = initial
            }
        }
        true
    }
}

fun <T> String.jsonToArraylistObject():ArrayList<T>{
    val json = this
    return Gson().fromJson(json, object: TypeToken<ArrayList<T>>(){}.type)
}

fun BottomSheetBehavior<*>.hide(){
    this.state = BottomSheetBehavior.STATE_HIDDEN
}
fun BottomSheetBehavior<*>.collapse(){
    this.state = BottomSheetBehavior.STATE_COLLAPSED
}
fun BottomSheetBehavior<*>.isShowing():Boolean{
    return (this.state == BottomSheetBehavior.STATE_COLLAPSED || this.state == BottomSheetBehavior.STATE_EXPANDED)
}
fun BottomSheetBehavior<*>.show(){
    this.state = BottomSheetBehavior.STATE_EXPANDED
}



