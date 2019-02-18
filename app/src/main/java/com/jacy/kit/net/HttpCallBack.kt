package com.jacy.kit.net

interface HttpCallBack {

    /**
     * 请求成功
     */
    fun onBegin() {}

    /**
     * 请求成功
     */
    fun onSuccess(result: Any?) {}

    /**
     * 请求失败
     */
    fun onError(msg: String) {}

    /**
     * 请求结束
     */
    fun onFinish() {}

}