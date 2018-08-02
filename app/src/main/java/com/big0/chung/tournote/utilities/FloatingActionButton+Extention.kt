package com.big0.chung.tournote.utilities

import android.animation.Animator
import android.animation.ValueAnimator
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.animation.LinearInterpolator

enum class TranslationType {
    X, Y
}

inline fun FloatingActionButton.rota(from: Float, to: Float) {
    // 1
    val valueAnimator = ValueAnimator.ofFloat(from, to)

    valueAnimator.addUpdateListener {
        val value = it.animatedValue as Float
        // 2
        this.rotation = value
    }

    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = 350
    valueAnimator.start()
}

inline fun FloatingActionButton.translation(translationType: TranslationType, from: Float, to: Float, duration: Long = 3000, noinline complete: ()->Unit) {
    if (this.visibility == View.INVISIBLE) this.visibility = View.VISIBLE
    val valueAnimator = ValueAnimator.ofFloat(from, to)
    valueAnimator.addUpdateListener {
        val value = it.animatedValue as Float
        if (translationType == TranslationType.X) this.translationX = value else this.translationY = value
    }
    valueAnimator.addListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}
        override fun onAnimationEnd(animation: Animator?) {
            complete()
        }

        override fun onAnimationCancel(animation: Animator?) {}

        override fun onAnimationRepeat(animation: Animator?) {}
    })
    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = duration
    valueAnimator.start()
}