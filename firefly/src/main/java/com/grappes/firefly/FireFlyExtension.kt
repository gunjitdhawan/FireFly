package com.grappes.firefly

import android.view.View
import java.util.*
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.R
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.ViewGroup
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.graphics.drawable.GradientDrawable



var glowWidth = 0


fun View.glow(shape: Shape) {
    this.post {
        var bgView = View(context)

        glowWidth = pxFromDp(context, 20F)
        var params : ConstraintLayout.LayoutParams = layoutParams as ConstraintLayout.LayoutParams

        var newParams = ConstraintLayout.LayoutParams(width+glowWidth, height+glowWidth)
        newParams.leftToLeft = params.leftToLeft;
        newParams.rightToRight = params.rightToRight;
        newParams.topToBottom = params.topToBottom;
        newParams.bottomToTop = params.bottomToTop;
        newParams.topToTop = params.topToTop;
        newParams.bottomToBottom = params.bottomToBottom;
        newParams.verticalChainStyle = params.verticalChainStyle;
        newParams.topMargin = params.topMargin;
        newParams.bottomMargin = params.bottomMargin;
        newParams.marginEnd = params.marginEnd;
        newParams.marginStart = params.marginStart;
        newParams.setMargins(params.leftMargin, params.topMargin, params.rightMargin, params.bottomMargin)

        bgView.layoutParams = newParams


        if(elevation==0.0f) {
            elevation = 10f
            bgView.elevation = 9f
        } else {
          bgView.elevation = elevation*0.95f
        }

        val gd = GradientDrawable()
        if(shape==Shape.CIRCLE) {
            gd.setColor(Color.CYAN)
            gd.shape = GradientDrawable.OVAL
        } else {
            gd.setColor(Color.YELLOW)
            gd.cornerRadius = pxFromDp(context, 10F).toFloat()
        }

        bgView.background = gd



        var parent : ViewGroup = this.parent as ViewGroup
        parent.addView(bgView)

        val animation1 = AlphaAnimation(0f, 0.8f)
        animation1.duration = 1000
        animation1.startOffset = 0
        animation1.repeatCount = 15
        animation1.fillAfter = false
        animation1.repeatMode = Animation.REVERSE
        animation1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                parent.removeView(bgView)
            }

        })

        bgView.startAnimation(animation1)
    }

}


fun dpFromPx(context: Context, px: Float): Int {
    return (px / context.resources.displayMetrics.density).toInt()
}

fun pxFromDp(context: Context, dp: Float): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}

enum class Shape {RECTANGLE, CIRCLE}