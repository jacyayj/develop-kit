package com.jacy.kit.net

import com.zhouyou.http.callback.CallBack
import com.zhouyou.http.exception.ApiException
import com.zhouyou.http.model.ApiResult

open class CommonCallBack<T : ApiResult<C>, C>(private val callBack: HttpCallBack) : CallBack<T>() {

    override fun onSuccess(t: T) {
        if (t.isOk)
            callBack.onSuccess(t)
        else
            callBack.onError(t.msg)
    }

    override fun onError(e: ApiException?) {
        callBack.onError(e?.displayMessage ?: e?.localizedMessage ?: e?.message
        ?: "未知错误 : ${e?.code}")
    }

    override fun onStart() {
        callBack.onBegin()
    }

    override fun onCompleted() {
        callBack.onFinish()
    }
}