package com.jacy.kit.utils

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import com.google.gson.Gson
import java.util.*

private val activityStack by lazy { Stack<Activity>() }

fun Activity.addActivity() {
    activityStack.add(this)
}

fun Activity.removeActivity() {
    activityStack.remove(this)
}

fun Any.currentActivity() = activityStack.lastElement()

fun Activity.mStartActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Activity.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
    val i = Intent(this, cls)
    arg.forEach {
        when (val value = it.second) {
            is String -> i.putExtra(it.first, value)
            is Int -> i.putExtra(it.first, value)
            is Float -> i.putExtra(it.first, value)
            is Boolean -> i.putExtra(it.first, value)
            is Parcelable -> i.putExtra(it.first, value)
            else -> i.putExtra(it.first, Gson().toJson(value))
        }
    }

    startActivity(i)
}