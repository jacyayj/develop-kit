package com.jacy.kit.config

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jacy.kit.net.HttpCallBack
import com.jacy.kit.weight.LoadingDialog
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallBack
import com.zhouyou.http.model.HttpParams
import io.reactivex.disposables.Disposable

abstract class RootFragment : Fragment(), HttpCallBack {

    private var isPrepare = false
    private var isFirst = true

    private var httpCount = 0

    private val loadingDialog by lazy {
        initLoading().apply {
            setOnDismissListener {
                httpPool.forEach {
                    EasyHttp.cancelSubscription(it)
                }
                httpPool.clear()
            }
        }
    }

    private val httpPool by lazy { ArrayList<Disposable>() }
    private val backgroundHttpPool by lazy { ArrayList<Disposable>() }

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

    open fun postUrl(url: String, params: HttpParams, callBack: CallBack<*>, showLoading: Boolean) {
        val disposable = EasyHttp.post(url).params(params).execute(callBack)
        if (showLoading)
            httpPool.add(disposable)
        else
            backgroundHttpPool.add(disposable)
    }

    override fun onBegin(showLoading: Boolean,url: String) {
        if (showLoading) {
            if (httpCount == 0)
                loadingDialog.show()
            httpCount++
        }
    }

    override fun onFinish(url: String) {
        if (httpCount > 0) {
            httpCount--
            if (httpCount == 0)
                loadingDialog.dismiss()
        }
    }

    open fun initLoading(): Dialog {
        return LoadingDialog(context!!)
    }


    open fun initData() {
    }

    open fun initListener() {}
    open fun notifyDateSetChanged(type: Int) {}
    open fun onVisible() {}
    open fun onInvisible() {}
    open fun onBackPressed() {}

}
