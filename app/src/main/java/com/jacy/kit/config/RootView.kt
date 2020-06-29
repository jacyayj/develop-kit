package com.jacy.kit.config

interface RootView {

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化监听器
     */
    fun initListener() {}

    /**
     * 返回键重写
     */
    fun back() {}

    /**
     * 显示loading弹窗
     */
    fun showLoading() {}

    /**
     * 隐藏loading弹窗
     */
    fun hideLoading() {}
}