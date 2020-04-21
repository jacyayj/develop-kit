package com.jacy.kit.config

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.jacy.develop.kit.R
import com.tamsiree.rxtool.RxDeviceTool

open class BaseDialog(
    context: Context,
    private val gravity: Int = Gravity.NO_GRAVITY,
    private val with: Float = -1f,
    private val height: Float = -1f
) :
    AlertDialog(context, R.style.Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDialog()
        initData()
        initListener()
    }

    private fun initDialog() {

        val p = window?.attributes // 获取对话框当前的参数值
        if (with != -1f)
            p?.width = (RxDeviceTool.getScreenWidth(context) * with).toInt()// 宽度设置为屏幕的占比
        if (height != -1f)
            p?.height = (RxDeviceTool.getScreenHeight(context) * with).toInt()// 高度设置为屏幕的占比
        window?.setGravity(gravity)
        window?.attributes = p
    }

    open fun initData() {

    }

    open fun initListener() {

    }

}