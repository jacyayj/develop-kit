package com.jacy.kit.net

import com.zhouyou.http.model.HttpParams

class MyParams : HttpParams {

    constructor(vararg values: Pair<String, Any>) {
        values.forEach {
            put(it.first, it.second.toString())
        }
    }

}