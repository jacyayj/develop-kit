package com.jacy.kit.utils

import android.view.Gravity
import android.view.Window
import com.vondear.rxtool.RxDeviceTool
import com.vondear.rxtool.RxTool

/**
 * project:SRC
 * author  TP
 * date:  2016/11/21 11:39
 * describe:
 */

object DialogUtils {

    fun initDialogWidth(window: Window, widthScale: Float) {
        val width = RxDeviceTool.getScreenWidth(RxTool.getContext())
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (width * widthScale).toInt() // 宽度设置为屏幕的占比
        window.attributes = p
    }

    fun initDialogWidthAndHeight(window: Window, widthScale: Float, heightScale: Float) {
        val width = RxDeviceTool.getScreenWidth(RxTool.getContext())
        val height = RxDeviceTool.getScreenHeight(RxTool.getContext())
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (width * widthScale).toInt() // 宽度设置为屏幕的占比
        p.height = (height * heightScale).toInt() // 高度设置为屏幕的占比
        window.attributes = p
    }

    fun setBottom(window: Window, scale: Float = 1f) {
        val width = RxDeviceTool.getScreenWidth(RxTool.getContext())
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (width * scale).toInt()// 宽度设置为屏幕的占比
        window.setGravity(Gravity.BOTTOM)
        window.attributes = p
    }

    fun setTop(window: Window, scale: Float = 1f) {
        val width = RxDeviceTool.getScreenWidth(RxTool.getContext())
        val p = window.attributes // 获取对话框当前的参数值
        p.width = (width * scale).toInt()// 宽度设置为屏幕的占比
        window.setGravity(Gravity.TOP)
        window.attributes = p
    }
}
