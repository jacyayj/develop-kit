package com.jacy.kit.utils

import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.DrawableMarginSpan
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import com.jacy.kit.config.dp2px


fun String.setColor(color: Int, start: Int, end: Int): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return ssb
}

fun String.setSize(size: Int, start: Int, end: Int): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(AbsoluteSizeSpan(size, true), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return ssb
}

fun String.setLeading(leading: Float): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(
        LeadingMarginSpan.Standard(dp2px(leading), 0),
        0,
        length,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    return ssb
}

fun String.setImage(drawable: Drawable, margin: Float, index: Int): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(
        DrawableMarginSpan(drawable, dp2px(margin)),
        index,
        index + 1,
        Spannable.SPAN_INCLUSIVE_INCLUSIVE
    )
    return ssb
}
