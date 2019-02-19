package com.jacy.kit.config

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jacy.kit.net.CommonCallBack
import com.jacy.kit.net.HttpCallBack
import com.jacy.kit.weight.LoadingDialog
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.model.HttpParams

abstract class RootFragment : Fragment(), HttpCallBack {

    private var isPrepare = false
    private var isFirst = true

    private var httpCount = 0

    private val loadingDialog by lazy { initLoading() }

    fun getLayoutId(): Int {
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

    fun request(url: String, params: HttpParams, callBack: CommonCallBack<*, *>) {
        EasyHttp.post(url)
            .params(params)
            .execute(callBack)
    }

    override fun onBegin() {
        if (showLoading()) {
            if (httpCount == 0)
                loadingDialog.show()
            httpCount++
        }
    }

    override fun onFinish() {
        if (showLoading()) {
            httpCount--
            if (httpCount == 0)
                loadingDialog.show()
        }
    }

    fun showLoading() = true

    fun initLoading(): Dialog = LoadingDialog(context!!)


    open fun initData() {
    }

    open fun initListener() {}
    open fun notifyDateSetChanged(type: Int) {}
    open fun onVisible() {}
    open fun onInvisible() {}
    open fun onBackPressed() {}

}
