package com.jacy.kit.utils

import com.google.gson.GsonBuilder
import com.orhanobut.logger.Logger

object mlog {

    var TAG = "jacy_log"

    private val gson by lazy { GsonBuilder().setPrettyPrinting().create() }

    fun v(msg: String?) {
        Logger.v(msg ?: "msg is null")
    }

    fun json(msg: Any?) {
        Logger.json(msg?.let { gson.toJson(it) } ?: "msg is null")
    }

}