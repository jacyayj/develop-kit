package com.jacy.kit.config

import android.app.Application
import com.jacy.kit.utils.application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

open class RootApp : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}