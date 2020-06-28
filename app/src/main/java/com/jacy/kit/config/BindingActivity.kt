package com.jacy.kit.config

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.jacy.kit.utils.getLayoutId

/**
 * Created by jacy on 2018/12/19.
 * 根activity，初始化各种通用数据；
 */
abstract class BindingActivity<T : ViewDataBinding> : RootActivity() {

    protected val binding: T by lazy { DataBindingUtil.setContentView<T>(this, getLayoutId()) }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()
    }
}
