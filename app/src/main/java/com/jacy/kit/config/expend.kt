package com.jacy.kit.config

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vondear.rxtool.RxTool

/**
 * Created by Administrator on 2018/3/19.
 */


fun Any.toJson() = GsonBuilder().disableInnerClassSerialization().create().toJson(this)

fun Any.toJsonPretty(): String =
    GsonBuilder().disableInnerClassSerialization().setPrettyPrinting().create().toJson(this)

fun Any.toPrettyString(): String =
    GsonBuilder().disableInnerClassSerialization().setPrettyPrinting().create().toJson(this)

fun Any.toJsonWithExpose() =
    GsonBuilder().disableInnerClassSerialization().excludeFieldsWithoutExposeAnnotation().create().toJson(
        this
    )

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

fun <T> Intent.getObject(key: String, clz: Class<*>): T =
    Gson().fromJson<T>(getStringExtra(key), clz)

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

fun Activity.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(this, Intent(this, cls), requestCode, null)
}

fun Context.mStartActivity(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Context.mStartActivity(cls: Class<*>, vararg arg: Pair<String, *>) {
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

fun Context.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(this as Activity, Intent(this, cls), requestCode, null)
}

fun Context.mStartActivityForResult(cls: Class<*>, requestCode: Int, vararg arg: Pair<String, *>) {
    val i = Intent(this, cls)
    arg.forEach {
        when (val value = it.second) {
            is String -> i.putExtra(it.first, value)
            is Int -> i.putExtra(it.first, value)
            is Float -> i.putExtra(it.first, value)
            is Boolean -> i.putExtra(it.first, value)
            is ArrayList<*> -> i.putStringArrayListExtra(
                it.first,
                value as java.util.ArrayList<String>?
            )
            is Parcelable -> i.putExtra(it.first, value)
            else -> i.putExtra(it.first, Gson().toJson(value))
        }
    }
    startActivityForResult(this as Activity, i, requestCode, null)
}

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

fun Fragment.mStartActivityForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(Intent(activity, cls), requestCode)
}

fun Fragment.mStartActivityForResult(cls: Class<*>, requestCode: Int, vararg arg: Pair<String, *>) {
    val i = Intent(activity, cls)
    arg.forEach {
        when (val value = it.second) {
            is String -> i.putExtra(it.first, value)
            is Int -> i.putExtra(it.first, value)
            is Float -> i.putExtra(it.first, value)
            is Boolean -> i.putExtra(it.first, value)
            is ArrayList<*> -> i.putStringArrayListExtra(
                it.first,
                value as java.util.ArrayList<String>?
            )
            is Parcelable -> i.putExtra(it.first, value)
            else -> i.putExtra(it.first, Gson().toJson(value))
        }
    }
    startActivityForResult(i, requestCode)
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

fun toast(msg: CharSequence?) {
    Toast.makeText(RxTool.getContext(), msg, Toast.LENGTH_SHORT).show()
}

fun toast(resId: Int) {
    Toast.makeText(RxTool.getContext(), resId, Toast.LENGTH_SHORT).show()
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

/**
 * 获取layoutId
 */
fun Any.getLayoutId(): Int {
    return if (javaClass.isAnnotationPresent(ContentView::class.java)) {
        val field = javaClass.getAnnotation(ContentView::class.java)
        field?.layoutId ?: -1
    } else {
        throw NullPointerException("未设置页面layoutId")
    }
}

fun Context.copyToClipboard(text: CharSequence) {
    val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val cp = ClipData.newPlainText("label", text)
    cm.setPrimaryClip(cp)
}
