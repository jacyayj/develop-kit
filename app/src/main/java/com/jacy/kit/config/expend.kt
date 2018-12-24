package com.jacy.kit.config

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import com.vondear.rxtool.RxTool

/**
 * Created by Administrator on 2018/3/19.
 */


fun Any.toJson() = Gson().toJson(this)

@SuppressLint("NewApi")
fun Context.mgetColor(id: Int) = resources.getColor(id)

@SuppressLint("NewApi")
fun Context.mgetDrawable(id: Int): Drawable = resources.getDrawable(id)

fun Context.mgetDimension(id: Int): Int = resources.getDimensionPixelSize(id)

fun Fragment.getIntArray(id: Int) = resources.getIntArray(id)

fun Fragment.getStringArray(id: Int) = resources.getStringArray(id)

fun View.gone() {
    if (visibility != View.GONE)
        visibility = View.GONE
}

fun View.show() {
    if (visibility != View.VISIBLE)
        visibility = View.VISIBLE
}

fun View.hide() {
    if (visibility != View.INVISIBLE)
        visibility = View.INVISIBLE
}

fun View.isShowing() = visibility == View.VISIBLE

fun <T> Intent.getObject(key: String, clz: Class<*>): T = Gson().fromJson<T>(getStringExtra(key), clz)

fun Activity.mStartActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Activity.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
    val i = Intent(this, cls)
    arg.forEach {
        val value = it.second
        when (value) {
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

fun Activity.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(this, Intent(this, cls), requestCode, null)
}

fun Context.mStartActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Context.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
    val i = Intent(this, cls)
    arg.forEach {
        val value = it.second
        when (value) {
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

fun Context.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(this as Activity, Intent(this, cls), requestCode, null)
}

fun Context.mStartActivityForResult(cls: Class<*>, requestCode: Int, vararg arg: Pair<String, *>) {
    val i = Intent(this, cls)
    arg.forEach {
        val value = it.second
        when (value) {
            is String -> i.putExtra(it.first, value)
            is Int -> i.putExtra(it.first, value)
            is Float -> i.putExtra(it.first, value)
            is Boolean -> i.putExtra(it.first, value)
            is ArrayList<*> -> i.putStringArrayListExtra(it.first, value as java.util.ArrayList<String>?)
            is Parcelable -> i.putExtra(it.first, value)
            else -> i.putExtra(it.first, Gson().toJson(value))
        }
    }
    startActivityForResult(this as Activity, i, requestCode, null)
}

fun Fragment.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
    val i = Intent(context, cls)
    arg.forEach {
        val value = it.second
        when (value) {
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

fun Fragment.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(Intent(context, cls), requestCode)
}

/**
 * 功能：判断字符串是否为数字
 *
 * @param str
 * @return
 */
fun String.isZero(): Boolean {
    return if (this.isNullOrEmpty())
        true
    else
        this == "0"
}

private var toastor: Toast? = null

fun toast(msg: String?) {
    if (toastor == null)
        toastor = Toast.makeText(RxTool.getContext(), msg, Toast.LENGTH_SHORT)
    else
        toastor?.setText(msg)
    toastor?.show()
}

fun Any.copy(obj: Any) {
    val fields = javaClass.declaredFields
    fields.forEach {
        it.isAccessible = true
        val targetFiled = obj.javaClass.getDeclaredField(it.name)
        if (it.name != "id") {
            targetFiled.isAccessible = true
            it.set(this, targetFiled.get(obj))
        }
    }
}
