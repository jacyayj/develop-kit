package com.jacy.develop.kit.config

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vondear.rxtool.RxActivityTool
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.ProgressDialogCallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.subsciber.IProgressDialog

/**
 * Created by jacy on 2018/7/24.
 * 根activity，初始化各种通用数据；
 */
abstract class HttpActivity<T> : AppCompatActivity() {

    var loadingProgress: IProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDatabinding()
        initData()
        initListener()
        RxActivityTool.addActivity(this)
    }

    override fun onDestroy() {
        RxActivityTool.getActivityStack().remove(this)
        super.onDestroy()
    }

    /**
     * 设置页面layout
     */
    internal fun getLayoutId(): Int {
        return if (javaClass.isAnnotationPresent(ContentView::class.java)) {
            val field = javaClass.getAnnotation(ContentView::class.java)
            field.layoutId
        } else {
            throw NullPointerException("activity 未设置页面layoutId")
        }
    }

    internal fun post(url: String) {
        EasyHttp.post(url)
            .execute(object : ProgressDialogCallBack<T>(loadingProgress, true, true) {
                override fun onSuccess(p0: T?) {

                }

                override fun onError(e: ApiException?) {
                    super.onError(e)
                    toast(e?.message)
                }
            })
    }

    /**
     * 初始化数据
     */
    open fun initData() {}

    /**
     * 获取数据
     */
    open fun fetchData() {}

    /**
     * 初始化监听器
     */
    open fun initListener() {}

    /**
     * 初始化databinding
     */
    open fun initDatabinding() {}

    open fun back(view: View) {
        onBackPressed()
    }
}
