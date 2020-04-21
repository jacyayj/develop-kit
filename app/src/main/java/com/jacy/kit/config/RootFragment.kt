package com.jacy.kit.config

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jacy.kit.net.HttpCallBack
import com.jacy.kit.weight.LoadingDialog

abstract class RootFragment : Fragment(), HttpCallBack {

    private var isPrepare = false
    private var isFirst = true

    private var httpCount = 0

    private val loadingDialog by lazy {
        initLoading()
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
