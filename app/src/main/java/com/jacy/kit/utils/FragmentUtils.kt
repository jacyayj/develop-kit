package com.jacy.kit.utils

import android.content.Intent
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.google.gson.Gson

fun Fragment.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
    val i = Intent(activity, cls)
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
