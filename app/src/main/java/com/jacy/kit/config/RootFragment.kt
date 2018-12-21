package com.jacy.kit.config

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.ProgressDialogCallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.model.ApiResult
import com.zhouyou.http.model.HttpParams
import com.zhouyou.http.subsciber.IProgressDialog

abstract class RootFragment<T : ApiResult<*>> : Fragment() {
    private val loadingProgress: IProgressDialog by lazy { IProgressDialog { initProgress() } }
    private var isPrepare = false
    private var isFirst = true


    private fun getLayoutId(): Int {
        return if (javaClass.isAnnotationPresent(ContentView::class.java)) {
            val field = javaClass.getAnnotation(ContentView::class.java)
            field.layoutId
        } else {
            throw NullPointerException("fragment 未设置页面layoutId")
        }
    }

    open fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View =
        inflater.inflate(getLayoutId(), container, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        getLayoutView(inflater, container)

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isPrepare.not()) return
        if (isVisibleToUser) {
            if (isFirst) {
                initData()
                initListener()
                isFirst = false
            }
            onVisible()
        } else
            onInvisible()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isPrepare.not()) {
            isPrepare = true
            if (userVisibleHint) {
                if (isFirst) {
                    initData()
                    initListener()
                    isFirst = false
                }
                onVisible()
            } else
                onInvisible()
        }
    }

    internal fun post(
        url: String,
        params: HttpParams,
        success: (result: T) -> Unit = {},
        error: (msg: String) -> Unit = { toast(it) },
        showProgress: Boolean = true
    ) {
        EasyHttp.post(url)
            .params(params)
            .execute(object : ProgressDialogCallBack<T>(loadingProgress, showProgress, true) {
                override fun onSuccess(result: T?) {
                    result?.let {
                        if (it.isOk) {
                            success(it)
                            this@RootFragment.onSuccess(it)
                        } else {
                            error(it.msg)
                            this@RootFragment.onError(it.msg)
                        }
                    }
                }

                override fun onError(e: ApiException?) {
                    super.onError(e)
                    error(e?.message ?: e?.displayMessage ?: "${e?.code}")
                    this@RootFragment.onError(e?.message ?: e?.displayMessage ?: "${e?.code}")
                }

                override fun onCompleted() {
                    super.onCompleted()
                    onFinish()
                }
            })
    }

    abstract fun initProgress(): ProgressDialog

    open fun initData() {
    }

    /**
     * 请求成功
     */
    open fun onSuccess(result: T) {}

    /**
     * 请求失败
     */
    open fun onError(msg: String) {}

    /**
     * 请求结束
     */
    open fun onFinish() {}

    open fun initListener() {}
    open fun notifyDateSetChanged(type: Int) {}
    open fun onVisible() {}
    open fun onInvisible() {}
    open fun onBackPressed() {}

}
