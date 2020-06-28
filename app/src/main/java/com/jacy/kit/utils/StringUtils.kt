package com.jacy.kit.utils

import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.DrawableMarginSpan
import android.text.style.ForegroundColorSpan
import android.text.style.LeadingMarginSpan
import java.util.regex.Pattern

/**
 * 设置文字颜色
 */
fun String.setColor(color: Int, start: Int, end: Int): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return ssb
}

/**
 * 设置文字大小
 */
fun String.setSize(size: Int, start: Int, end: Int): SpannableStringBuilder {
    val ssb = SpannableStringBuilder(this)
    ssb.setSpan(AbsoluteSizeSpan(size, true), start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    return ssb
}

/**
 * 设置文字缩进
 */
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

/**
 * 设置文字图片
 */
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

/**
 * 功能：判断字符串是否为数字
 *
 * @return
 */
fun String.isZero(): Boolean {
    return if (this.isNullOrEmpty())
        true
    else
        this == "0"
}

/**
 * 验证字符串是否为手机号
 * @param isSimple 是否简单验证
 */
fun String.isMobile(isSimple: Boolean = true): Boolean {
    val p =
        Pattern.compile(if (isSimple) "^[1]\\d{10}$" else "^(13[0-9]|15[012356789]|17[03678]|18[0-9]|14[57])[0-9]{8}$")
    val m = p.matcher(this)
    return m.matches()
}

/**
 * 验证银卡卡号
 * @return
 */
fun String.isBankCard(): Boolean {
    val p =
        Pattern.compile("^\\d{16,19}$|^\\d{6}[- ]\\d{10,13}$|^\\d{4}[- ]\\d{4}[- ]\\d{4}[- ]\\d{4,7}$")
    val m = p.matcher(this)
    return m.matches()
}

/**
 * 验证邮箱
 * @return
 */
fun String.isEmail(): Boolean {
    val p =
        Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    val m = p.matcher(this)
    return m.matches()
}


/**
 * 验证身份证
 * @return
 */
fun String.isIdCard(): Boolean {
    val p =
        Pattern.compile("(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X)$)")
    val m = p.matcher(this)
    return m.matches()
}
