package com.jacy.kit.config

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import com.jacy.develop.kit.R
import com.vondear.rxtool.RxDeviceTool

open class BaseDialog(
    context: Context,
    private val gravity: Int = Gravity.NO_GRAVITY,
    private val with: Float = 0.8f
) :
    AlertDialog(context, R.style.Theme_dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDialog()
        initData()
        initListener()
    }

    private fun initDialog() {
        val width = RxDeviceTool.getScreenWidth(context)
        val p = window?.attributes // 获取对话框当前的参数值
        p?.width = (width * with).toInt()// 宽度设置为屏幕的占比
        window?.setGravity(gravity)
        window?.attributes = p
    }

    open fun initData() {

    }

    open fun initListener() {

    }

}