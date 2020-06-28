package com.jacy.kit.config

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jacy.kit.utils.getLayoutId

abstract class RootFragment : Fragment(),RootView{

    private var isPrepare = false
    private var isFirst = true


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

    open fun notifyDateSetChanged(type: Int) {}
    open fun onVisible() {}
    open fun onInvisible() {}
}
