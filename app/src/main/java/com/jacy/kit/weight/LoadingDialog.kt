package com.jacy.kit.weight

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.jacy.develop.kit.R

class LoadingDialog(context: Context) : Dialog(context, R.style.Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_loading)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
    }
}