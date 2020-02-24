package com.jacy.kit.utils

import com.google.gson.GsonBuilder
import com.tamsiree.rxtool.RxLogTool

object log {

    var TAG = "jacy_log"

    private val gson by lazy { GsonBuilder().setPrettyPrinting().create() }

    fun v(msg: String?) {
        RxLogTool.v(TAG, msg ?: "msg is null")
    }

    fun json(msg: Any?) {
        RxLogTool.v(TAG, msg?.let { gson.toJson(it) } ?: "msg is null")
    }

}