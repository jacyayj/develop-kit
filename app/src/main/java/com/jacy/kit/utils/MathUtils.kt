package com.jacy.kit.utils

import com.vondear.rxtool.RxImageTool
import com.vondear.rxtool.RxRecyclerViewDividerTool
import java.math.MathContext
import java.math.RoundingMode


/**
 * 四舍五入
 */
fun Any.toRoundInt(): Int = when (this) {
    is String -> toBigDecimalOrNull(MathContext(0))?.intValueExact() ?: 0
    is Float -> toBigDecimal(MathContext(0)).intValueExact()
    is Double -> toBigDecimal(MathContext(0)).intValueExact()
    else -> 0
}


/**
 * 向上取整
 */
fun Any.toUpInt(): Int = when (this) {
    is String -> toBigDecimalOrNull(MathContext(0, RoundingMode.CEILING))?.intValueExact() ?: 0
    is Float -> toBigDecimal(MathContext(0, RoundingMode.CEILING)).intValueExact()
    is Double -> toBigDecimal(MathContext(0, RoundingMode.CEILING)).intValueExact()
    else -> 0
}


/**
 * 向下取整
 */
fun Any.toDownInt(): Int = when (this) {
    is String -> toBigDecimalOrNull(MathContext(0, RoundingMode.FLOOR))?.intValueExact() ?: 0
    is Float -> toBigDecimal(MathContext(0, RoundingMode.FLOOR)).intValueExact()
    is Double -> toBigDecimal(MathContext(0, RoundingMode.FLOOR)).intValueExact()
    else -> 0
}

/**
 * @param pointSize 保留几位小数
 *  四舍五入
 */
fun Any.toRounFloat(pointSize: Int): Float = when (this) {
    is String -> toBigDecimalOrNull(MathContext(pointSize))?.toFloat() ?: 0f
    is Float -> toBigDecimal(MathContext(pointSize)).toFloat()
    is Double -> toBigDecimal(MathContext(pointSize)).toFloat()
    else -> 0f
}

/**
 * @param pointSize 保留几位小数
 *  向上取几位小数
 */
fun Any.toUpFloat(pointSize: Int): Float = when (this) {
    is String -> toBigDecimalOrNull(MathContext(pointSize, RoundingMode.CEILING))?.toFloat() ?: 0f
    is Float -> toBigDecimal(MathContext(pointSize, RoundingMode.CEILING)).toFloat()
    is Double -> toBigDecimal(MathContext(pointSize, RoundingMode.CEILING)).toFloat()
    else -> 0f
}

/**
 * @param pointSize 保留几位小数
 *  向下取几位小数
 */
fun Any.toDownFloat(pointSize: Int): Float = when (this) {
    is String -> toBigDecimalOrNull(MathContext(pointSize, RoundingMode.FLOOR))?.toFloat() ?: 0f
    is Float -> toBigDecimal(MathContext(pointSize, RoundingMode.FLOOR)).toFloat()
    is Double -> toBigDecimal(MathContext(pointSize, RoundingMode.FLOOR)).toFloat()
    else -> 0f
}