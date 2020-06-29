package com.jacy.kit.utils

import android.view.View

fun View.gone() {
    if (visibility != View.GONE)
        visibility = View.GONE
}

fun View.show() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}

fun View.hide() {
    if (visibility != View.INVISIBLE)
        visibility = View.INVISIBLE
}

fun View.isShowing() = visibility == View.VISIBLE