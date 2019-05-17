package com.jacy.kit.config

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jacy.kit.net.HttpCallBack
import com.jacy.kit.weight.LoadingDialog
import com.vondear.rxtool.RxActivityTool
import com.zhouyou.http.EasyHttp
import com.zhouyou.http.callback.CallBack
import io.reactivex.disposables.Disposable

/**
 * Created by jacy on 2018/12/19.
 * 根activity，初始化各种通用数据；
 */
abstract class RootActivity : AppCompatActivity(), HttpCallBack {

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
    open fun getLayoutId(): Int {
        return if (javaClass.isAnnotationPresent(ContentView::class.java)) {
            val field = javaClass.getAnnotation(ContentView::class.java)
            field.layoutId
        } else {
            throw NullPointerException("activity 未设置页面layoutId")
        }
    }

    open fun postUrl(url: String, callBack: CallBack<*>, showLoading: Boolean) {
        val disposable = EasyHttp.post(url).execute(callBack)
        if (showLoading)
            httpPool.add(disposable)
        else
            backgroundHttpPool.add(disposable)
    }

    override fun onBegin(showLoading: Boolean) {
        if (showLoading) {
            if (httpCount == 0)
                loadingDialog.show()
            httpCount++
        }
    }

    override fun onFinish() {
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

    /**
     * 初始化databinding
     */
    open fun initDatabinding() {}

    override fun onDestroy() {
        backgroundHttpPool.forEach {
            EasyHttp.cancelSubscription(it)
        }
        backgroundHttpPool.clear()
        RxActivityTool.getActivityStack().remove(this)
        super.onDestroy()
    }

    open fun back(view: View) {
        onBackPressed()
    }
}
