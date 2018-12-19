package com.jacy.develop.kit.config

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import com.jacy.develop.kit.BR
import java.lang.reflect.ParameterizedType

abstract class DataBindingFragment<T> : BaseFragment() {

    open val model: T by lazy { getClassInstance().newInstance() }
    private val binding by lazy { view?.let { DataBindingUtil.bind<ViewDataBinding>(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.setVariable(BR.model, model)
        super.onViewCreated(view, savedInstanceState)
    }

    /**
     * 获取泛型class对象
     * @param index 多个泛型时泛型索引，取第几个泛型
     */
    open fun getClassInstance(index: Int = 0): Class<T> {
        val type = javaClass.genericSuperclass
        // 判断 是否泛型
        return if (type is ParameterizedType) {
            // 返回表示此类型实际类型参数的Type对象的数组.
            // 当有多个泛型类时，数组的长度就不是1了
            val ptype = type.actualTypeArguments
            ptype[index] as Class<T>  //将第一个泛型T对应的类返回
        } else {
            Any::class.java as Class<T>//若没有给定泛型，则返回Object类
        }
    }
}
