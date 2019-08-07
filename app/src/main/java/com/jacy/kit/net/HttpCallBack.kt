package com.jacy.kit.net

interface HttpCallBack {

    /**
     * 请求开始
     */
    fun onBegin(showLoading: Boolean,url: String) {}

    /**
     * 请求成功
     */
    fun onSuccess(url: String, result: Any?) {}

    /**
     * 请求失败
     */
    fun onError(msg: String, url: String) {}

    /**
     * 请求结束
     */
    fun onFinish(url: String) {}

}