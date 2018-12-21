package com.jacy.kit.utils

import com.google.gson.GsonBuilder
import com.vondear.rxtool.RxLogTool

object log {

    var TAG = "jacy_log"

    fun v(msg: String?) {
        RxLogTool.v(TAG, msg ?: "msg is null")
    }

    fun json(msg: Any?) {
        RxLogTool.v(TAG, msg?.let { GsonBuilder().setPrettyPrinting().create().toJson(it) } ?: "msg is null")
    }

}