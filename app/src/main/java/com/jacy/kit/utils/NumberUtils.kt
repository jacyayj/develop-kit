package com.jacy.kit.utils

import java.math.RoundingMode
import java.text.NumberFormat

private val numberFormat by lazy { NumberFormat.getNumberInstance().apply { minimumFractionDigits = 0 } }

fun Any.toRoundString(digits: Int = 2, isLong: Boolean = false): String {
    numberFormat.maximumFractionDigits = digits
    numberFormat.roundingMode = RoundingMode.HALF_UP
    numberFormat.isGroupingUsed = isLong
    return numberFormat.format(this)
}

fun Any.toUpString(digits: Int = 2, isLong: Boolean = false): String {
    numberFormat.maximumFractionDigits = digits
    numberFormat.roundingMode = RoundingMode.UP
    numberFormat.isGroupingUsed = isLong
    return numberFormat.format(this)
}

fun Any.toFloorString(digits: Int = 2, isLong: Boolean = false): String {
    numberFormat.maximumFractionDigits = digits
    numberFormat.roundingMode = RoundingMode.FLOOR
    numberFormat.isGroupingUsed = isLong
    return numberFormat.format(this)
}

