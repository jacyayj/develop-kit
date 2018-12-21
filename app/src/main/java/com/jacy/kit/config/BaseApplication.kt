package com.jacy.develop.kit.config

import android.app.Application
import com.vondear.rxtool.RxTool
import com.zhouyou.http.EasyHttp


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RxTool.init(this)
        EasyHttp.init(this)
        EasyHttp.getInstance()
            .setReadTimeOut(10 * 1000)
            .setWriteTimeOut(10 * 1000)
            .setConnectTimeout(10 * 1000)
            .debug("Jacy Http", true)
    }

}