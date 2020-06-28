package com.jacy.kit.config

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jacy.kit.utils.addActivity
import com.jacy.kit.utils.getLayoutId
import com.jacy.kit.utils.removeActivity

/**
 * Created by jacy on 2018/12/19.
 * 根activity，初始化各种通用数据；
 */
abstract class RootActivity : AppCompatActivity(), RootView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
        initListener()
        addActivity()
    }


    override fun onDestroy() {
        removeActivity()
        super.onDestroy()
    }

    open fun back(view: View) {
        onBackPressed()
    }
}
