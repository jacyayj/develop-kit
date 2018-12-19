package com.jacy.develop.kit.config

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

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

    open fun getLayoutView(inflater: LayoutInflater, container: ViewGroup?): View = inflater.inflate(getLayoutId(), container, false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = getLayoutView(inflater, container)

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

    open fun initData() {
    }

    open fun fetchData() {
    }

    open fun initListener() {}
    open fun notifyDateSetChanged(type: Int) {}
    open fun onVisible() {}
    open fun onInvisible() {}
    open fun onBackPressed() {}

}
