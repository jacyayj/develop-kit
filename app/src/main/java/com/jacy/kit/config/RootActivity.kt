package com.jacy.kit.config

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.jacy.kit.net.HttpCallBack
import com.jacy.kit.weight.LoadingDialog
import com.tamsiree.rxtool.RxActivityTool
import rxhttp.RxHttpPlugins

/**
 * Created by jacy on 2018/12/19.
 * 根activity，初始化各种通用数据；
 */
abstract class RootActivity : AppCompatActivity(), HttpCallBack {

    private var httpCount = 0

    private val loadingDialog by lazy {
        initLoading()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initListener()
        RxActivityTool.addActivity(this)
    }


    override fun onBegin(showLoading: Boolean, url: String) {
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
        return LoadingDialog(this)
    }

    /**
     * 初始化数据
     */
    open fun initData() {
    }

    /**
     * 初始化监听器
     */
    open fun initListener() {
    }

    fun getLoading(): Dialog {
        return loadingDialog
    }

    override fun onDestroy() {
        loadingDialog.dismiss()
        RxActivityTool.getActivityStack().remove(this)
        super.onDestroy()
    }

    open fun back(view: View) {
        onBackPressed()
    }
}
