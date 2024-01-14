package com.example.notetaking.utils.extension

import android.os.Handler
import android.os.Looper

fun delayHandler(durationInMillis: Long, block: () -> Unit) {
    Handler(Looper.getMainLooper())
        .postDelayed({ block.invoke() }, durationInMillis)
}