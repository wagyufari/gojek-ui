package com.mayburger.gojekuiclone.util.binding

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.mayburger.gojekuiclone.constants.RecyclerConstants
import com.mayburger.gojekuiclone.util.ext.ViewUtils
import com.mayburger.gojekuiclone.util.ext.ViewUtils.dpToPx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object ViewBinding {

    @BindingAdapter("android:layout_margin")
    @JvmStatic
    fun setLayoutMargin(view: View, margin: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val marg = ViewUtils.dpToPx(margin)
        params.setMargins(marg, marg, marg, marg)
        view.requestLayout()
    }

    @BindingAdapter("layout_marginBottomAnimate")
    @JvmStatic
    fun setLayoutMarginBottom(view: ViewGroup, margin: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val marg = ViewUtils.dpToPx(margin)
        val anim = ValueAnimator.ofInt(params.bottomMargin, marg)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, `val`)
            view.requestLayout()
        }
        anim.duration = 1000
        anim.start()
    }

    @BindingAdapter("paddingTopAnimate")
    @JvmStatic
    fun setPaddingTop(view: ViewGroup, padding: Int) {
        val anim = ValueAnimator.ofInt(view.paddingTop, dpToPx(padding))
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            view.setPadding(view.paddingLeft, `val`, view.paddingRight, view.paddingBottom)
            view.requestLayout()
        }
        anim.duration = 1000
        anim.start()
    }


    @BindingAdapter("paddingBottomAnimate")
    @JvmStatic
    fun setPaddingBottom(view: ViewGroup, padding: Int) {
        val anim = ValueAnimator.ofInt(view.paddingBottom, dpToPx(padding))
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, `val`)
            view.requestLayout()
        }
        anim.duration = 1000
        anim.start()
    }


    @BindingAdapter("radiusAnimate")
    @JvmStatic
    fun setRadiusAnimation(view: ViewGroup, radius: Int){
        val gd = view.background as GradientDrawable
        val anim = ValueAnimator.ofInt(gd.getCornerRadiusN(), radius)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            gd.cornerRadius = `val`.toFloat()
        }
        anim.start()
    }
    fun GradientDrawable.getCornerRadiusN():Int{
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            this.cornerRadius.toInt()
        } else{
            0
        }
    }

    @BindingAdapter("layout_marginLeftAnimate")
    @JvmStatic
    fun setLayoutMarginLeft(view: ViewGroup, margin: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val marg = ViewUtils.dpToPx(margin)
        val anim = ValueAnimator.ofInt(params.leftMargin, marg)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            params.setMargins(`val`, params.topMargin, params.rightMargin, params.bottomMargin)
            view.requestLayout()
        }
        anim.duration = 1000
        anim.start()
    }

    @BindingAdapter("layout_marginRightAnimate")
    @JvmStatic
    fun setLayoutMarginRight(view: ViewGroup, margin: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        val marg = ViewUtils.dpToPx(margin)
        val anim = ValueAnimator.ofInt(params.rightMargin, marg)
        anim.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            params.setMargins(params.leftMargin, params.topMargin, `val`, params.bottomMargin)
            view.requestLayout()
        }
        anim.duration = 1000
        anim.start()
    }

//    @BindingAdapter("layout_widthAnimate")
//    @JvmStatic
//    fun setWidth(view: ViewGroup, isMatchParent: Boolean) {
//        if (isMatchParent) {
//            val initialWidth = view.measuredWidth
//            val anim = ValueAnimator.ofInt(initialWidth, (view.parent as ViewGroup).width)
//            anim.addUpdateListener { valueAnimator ->
//                val `val` = valueAnimator.animatedValue as Int
//                val layoutParams: ViewGroup.LayoutParams = view.layoutParams
//                layoutParams.width = `val`
//                view.layoutParams = layoutParams
//            }
//            anim.duration = 1000
//            anim.start()
//        } else {
//            val params = view.layoutParams
//            val initialWidth = view.measuredWidth
//            params.width = WRAP_CONTENT
//            view.layoutParams = params
//            val targetWidth = view.measuredWidth
//        }
//    }

    @BindingAdapter("backgroundResource")
    @JvmStatic
    fun setBackgroundResource(view: ViewGroup, colorRes: Int) {
        view.setBackgroundColor(view.context.resources.getColor(colorRes))
    }

    @BindingAdapter("app:tabIndicatorColor")
    @JvmStatic
    fun setIndicatorColor(view: TabLayout, colorRes: Int) {
        view.setSelectedTabIndicatorColor(view.context.resources.getColor(colorRes))
    }

    @BindingAdapter("android:layout_alignParentBottom")
    @JvmStatic
    fun setAlignParentBottom(view: ViewGroup, alignParentBottom: Boolean) {
        val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
        if (alignParentBottom) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        } else {
            layoutParams.removeRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        }
        view.layoutParams = layoutParams
    }


    @BindingAdapter("onTouch")
    @JvmStatic
    fun setOnTouch(view: ViewGroup, runnable: Runnable) {
        view.setOnTouchListener { view, motionEvent ->
            when (motionEvent?.action) {
                MotionEvent.ACTION_DOWN -> {
                    runnable.run()
                }
            }
            true
        }
    }


    @BindingAdapter("animateLayoutChanges")
    @JvmStatic
    fun animateLayoutChanges(view: ViewGroup, animate: Boolean) {
        view.layoutTransition
            .enableTransitionType(LayoutTransition.CHANGING);
    }

    @BindingAdapter("recyclerLayoutManager")
    @JvmStatic
    fun horizontalLayoutManager(view: RecyclerView, id: Int) {
        val layoutManager: RecyclerView.LayoutManager? = when (id) {
            RecyclerConstants.VERTICAL_LAYOUT_MANAGER -> {
                LinearLayoutManager(view.context)
            }
            RecyclerConstants.HORIZONTAL_LAYOUT_MANAGER -> {
                val horizontalLinearLayoutManager = LinearLayoutManager(view.context)
                horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                horizontalLinearLayoutManager
            }
            RecyclerConstants.PAGER_MANAGER -> {
                PagerSnapHelper().attachToRecyclerView(view)
                LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
            }
            RecyclerConstants.GRID_2_LAYOUT_MANAGER -> {
                GridLayoutManager(view.context, 2)
            }
            RecyclerConstants.GRID_3_LAYOUT_MANAGER -> {
                GridLayoutManager(view.context, 3)
            }
            else -> {
                LinearLayoutManager(view.context)
            }
        }
        view.layoutManager = layoutManager
    }

    @BindingAdapter("visibility")
    @JvmStatic
    fun visibility(view: View, visibility: Int) {
        view.visibility = visibility
    }


    @BindingAdapter("delayVisibility")
    @JvmStatic
    fun delayVisibility(view: View, visibility: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            when (visibility) {
                View.GONE -> {
                    view.visibility = View.VISIBLE
                    delay(200)
                    view.visibility = View.GONE
                }
                View.VISIBLE -> {
                    view.visibility = View.GONE
                    delay(200)
                    view.visibility = View.VISIBLE
                }
            }
        }
    }

    @BindingAdapter("android:layout_height")
    @JvmStatic
    fun setHeight(view: View, isMatchParent: Boolean) {
        val params = view.layoutParams
        params.height = if (isMatchParent) MATCH_PARENT else WRAP_CONTENT
        view.layoutParams = params
    }

    @BindingAdapter("onClickAnimate")
    @JvmStatic
    fun setOnClickAnimate(view: View, runnable: Runnable) {
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
                }
            }
            true
        }
    }


}