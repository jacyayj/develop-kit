package com.jacy.kit.config

import android.app.Application
import com.jacy.kit.utils.application

open class RootApp : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}