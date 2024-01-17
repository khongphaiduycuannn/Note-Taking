package com.example.notetaking.view.animation

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

class ResizeHeightAnimation(
    private val view: View,
    private val startHeight: Int,
    private val endHeight: Int
) : Animation() {

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        view.layoutParams.height = startHeight + ((endHeight - startHeight) * interpolatedTime).toInt()
        view.requestLayout()
    }

    override fun willChangeBounds(): Boolean = true
}