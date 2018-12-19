package com.jwell56.usteel.utils

import android.view.Gravity
import android.view.Window

/**
 * project:SRC
 * author  TP
 * date:  2016/11/21 11:39
 * describe:
 */

object DialogUtils {

    fun initDialogWidth(window: Window, widthScale: Float) {
        val m = window.windowManager
        val d = m.defaultDisplay // 获取屏幕宽、高用
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (d.width * widthScale).toInt() // 宽度设置为屏幕的占比
        window.attributes = p
    }

    fun initDialogWidthAndHeight(window: Window, widthScale: Float, heightScale: Float) {
        val m = window.windowManager
        val d = m.defaultDisplay // 获取屏幕宽、高用
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (d.width * widthScale).toInt() // 宽度设置为屏幕的占比
        p.height = (d.height * heightScale).toInt() // 高度设置为屏幕的占比
        window.attributes = p
    }

    fun setBottom(window: Window, scale: Float = 1f) {
        val m = window.windowManager
        val d = m.defaultDisplay // 获取屏幕宽、高用
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (d.width * scale).toInt()// 宽度设置为屏幕的占比
        window.setGravity(Gravity.BOTTOM)
        window.attributes = p
    }

    fun setTop(window: Window, scale: Float = 1f) {
        val m = window.windowManager
        val d = m.defaultDisplay // 获取屏幕宽、高用
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (d.width * scale).toInt()// 宽度设置为屏幕的占比
        window.setGravity(Gravity.TOP)
        window.attributes = p
    }
}
