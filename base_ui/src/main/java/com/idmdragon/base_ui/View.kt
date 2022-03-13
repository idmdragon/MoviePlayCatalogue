package com.idmdragon.base_ui

import android.view.View
import androidx.core.view.isVisible

fun View.show() {
    isVisible = true
}

fun View.gone() {
    isVisible = false
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}