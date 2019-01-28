package com.jacy.kit.config

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vondear.rxtool.RxActivityTool
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.ProgressDialogCallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.model.ApiResult
import com.zhouyou.http.model.HttpParams
import com.zhouyou.http.subsciber.IProgressDialog

/**
 * Created by jacy on 2018/12/19.
 * 根activity，初始化各种通用数据；
 */
abstract class RootActivity : AppCompatActivity() {

    private val loadingProgress: IProgressDialog by lazy { IProgressDialog { initProgress() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initDatabinding()
        initData()
        initListener()
        RxActivityTool.addActivity(this)
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

    internal fun <T : ApiResult<*>> request(
        url: String,
        params: HttpParams,
        success: (result: T) -> Unit = {},
        error: (msg: String) -> Unit = { toast(it) },
        showProgress: Boolean = true
    ) {
        EasyHttp.post(url)
            .params(params)
            .execute(object : ProgressDialogCallBack<T>(loadingProgress, showProgress, false) {
                override fun onSuccess(result: T?) {
                    result?.let {
                        if (it.isOk) {
                            success(it)
                            this@RootActivity.onSuccess(it.data)
                        } else {
                            error(it.msg)
                            this@RootActivity.onError(it.msg)
                        }
                    }
                }

                override fun onError(e: ApiException?) {
                    super.onError(e)
                    error(e?.message ?: e?.displayMessage ?: "${e?.code}")
                    this@RootActivity.onError(e?.message ?: e?.displayMessage ?: "${e?.code}")
                }

                override fun onCompleted() {
                    super.onCompleted()
                    onFinish()
                }
            })
    }

    abstract fun initProgress(): ProgressDialog

    /**
     * 初始化数据
     */
    open fun initData() {
        TODO("进行数据的初始化")
    }

    /**
     * 请求成功
     */
    open fun onSuccess(result: Any) {

    }

    /**
     * 请求失败
     */
    open fun onError(msg: String) {}

    /**
     * 请求结束
     */
    open fun onFinish() {}

    /**
     * 初始化监听器
     */
    open fun initListener() {}

    /**
     * 初始化databinding
     */
    open fun initDatabinding() {}

    override fun onDestroy() {
        RxActivityTool.getActivityStack().remove(this)
        super.onDestroy()
    }

    open fun back(view: View) {
        onBackPressed()
    }
}
