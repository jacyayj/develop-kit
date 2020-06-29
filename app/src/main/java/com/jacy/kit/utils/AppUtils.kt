package com.jacy.kit.utils

import android.app.Activity
import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Parcelable
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jacy.kit.config.ContentView
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.lang.reflect.ParameterizedType

lateinit var application: Application

/**
 * 复制到粘贴板
 */
fun Context.copyToClipboard(text: CharSequence) {
    val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val cp = ClipData.newPlainText("label", text)
    cm.setPrimaryClip(cp)
}

/**
 * dp转px
 */
fun Any.dp2px(dp: Float): Int {
    return (Resources.getSystem().displayMetrics.density * dp + 0.5f).toInt()
}

/**
 * px转dp
 */
fun Any.px2dp(px: Int): Float {
    return Resources.getSystem().displayMetrics.density / px
}

/**
 * 获取res 颜色资源
 * @param id res资源ID
 */
fun Context.mgetColor(id: Int) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    resources.getColor(id, theme)
} else {
    resources.getColor(id)
}

/**
 * 获取res drawable资源
 * @param id res资源ID
 */
fun Context.mgetDrawable(id: Int): Drawable = resources.getDrawable(id, theme)

/**
 * 获取res 尺寸资源
 * @param id res资源ID
 */
fun Context.mgetDimension(id: Int): Int = resources.getDimensionPixelSize(id)

/**
 * 获取int数组资源
 * @param id res资源ID
 */
fun Context.getIntArray(id: Int) = resources.getIntArray(id)

/**
 * 获取res 字符串数组
 * * @param id res资源ID
 */
fun Context.getStringArray(id: Int) = resources.getStringArray(id).toMutableList()

/**
 * 将对象转换为json字符串
 */
fun Any.toJson() = GsonBuilder().disableInnerClassSerialization().create().toJson(this)

/**
 * 将对象转换为json字符串,带换行格式化
 */
fun Any.toJsonPretty(): String =
    GsonBuilder().disableInnerClassSerialization().setPrettyPrinting().create().toJson(this)

/**
 * 将对象转换为json字符串,忽略Expose注解字段
 */
fun Any.toJsonWithExpose() =
    GsonBuilder().disableInnerClassSerialization().excludeFieldsWithoutExposeAnnotation().create()
        .toJson(
            this
        )

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
    ActivityCompat.startActivityForResult(this as Activity, i, requestCode, null)
}

/**
 * toast提示
 * @param resId res资源Id
 */
fun toast(resId: Int) {
    Toast.makeText(application, resId, Toast.LENGTH_SHORT).show()
}

/**
 * toast提示
 * @param msg 字符串
 */
fun toast(msg: CharSequence?) {
    Toast.makeText(application, msg, Toast.LENGTH_SHORT).show()
}

/**
 * 复制一个对象
 */
fun Any.copy(obj: Any) {
    val fields = javaClass.declaredFields
    fields.forEach {
        it.isAccessible = true
        val targetFiled = obj.javaClass.getDeclaredField(it.name)
        targetFiled.isAccessible = true
        it.set(this, targetFiled.get(obj))
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

/**
 * 获取泛型class对象
 * @param index 多个泛型时泛型索引，取第几个泛型
 */
fun <T> Any.getClassInstance(index: Int = 0): Class<T> {
    val type = javaClass.genericSuperclass
    // 判断 是否泛型
    return if (type is ParameterizedType) {
        // 返回表示此类型实际类型参数的Type对象的数组.
        // 当有多个泛型类时，数组的长度就不是1了
        val ptype = type.actualTypeArguments
        ptype[index] as Class<T>  //将第一个泛型T对应的类返回
    } else {
        Any::class.java as Class<T>//若没有给定泛型，则返回Object类
    }
}

/**
 * 打开新的activity
 */
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

/**
 * 注册eventbus
 */
fun Any.registerEventBus() {
    EventBus.getDefault().register(this)
}

/**
 * 注销eventbus
 */
fun Any.unRegisterEventBus() {
    if (EventBus.getDefault().isRegistered(this))
        EventBus.getDefault().unregister(this)
}

/**
 * 通过eventbus发送事件
 * @param event 需要发送的事件
 * @param sticky 是否粘性消息
 */
fun Any.postEvent(event: Any, sticky: Boolean = false) {
    if (sticky)
        EventBus.getDefault().postSticky(event)
    else
        EventBus.getDefault().post(event)
}

/**
 * 获取屏幕高度
 */
fun Context.getScreenHeight(): Int {
    return (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.height
}

/**
 * 获取屏幕宽度
 */
fun Context.getScreenWidth(): Int {
    return (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.width
}

/**
 * 获取状态栏高度
 *
 * @return 状态栏高度
 */
fun Context.getStatusBar(): Int {
    var result = 0
    val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result
}

/**
 * 跳转到拨号盘
 * @param phone 拨打的号码
 */
fun Context.toDial(phone: String) {
    //跳转到拨号界面，同时传递电话号码
    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    ActivityCompat.startActivity(this, dialIntent, null)
}

/**
 * 发送短信
 *
 * @param phone 电话号码
 * @param message 短信内容
 */
fun Context.sendSms(phone: String, message: String) {
    val smsToUri = Uri.parse("smsto:${phone}")
    val intent = Intent(Intent.ACTION_SENDTO, smsToUri)
    intent.putExtra(
        "sms_body",
        message
    )
    ActivityCompat.startActivity(this, intent, null)
}

fun Context.installApk(filePath: String?, requestCode: Int) {
    //apk文件的本地路径
    val apkfile = File(filePath)
    if (!apkfile.exists()) {
        return
    }
    val intent = Intent(Intent.ACTION_VIEW)
    val contentUri: Uri =
        if (Build.VERSION.SDK_INT >= 24)
            FileProvider.getUriForFile(this, "$packageName.jacy.fileprovider", apkfile)
        else Uri.fromFile(apkfile)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    }
    intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
    ActivityCompat.startActivityForResult(this as Activity, intent, requestCode, null)
}




