package com.example.notetaking.view.animation

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeWidthAnimation(
    private val view: View,
    private val startWidth: Int,
    private val endWidth: Int
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.width = startWidth + ((endWidth - startWidth) * interpolatedTime).toInt()
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean = true
}