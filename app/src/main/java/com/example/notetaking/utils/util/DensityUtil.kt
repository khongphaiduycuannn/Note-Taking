package com.example.notetaking.utils.util

import android.content.Context

class DensityUtil {

    companion object {
        fun dpToPx(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }
    }
}