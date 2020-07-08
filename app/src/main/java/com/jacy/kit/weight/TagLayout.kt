package com.jacy.kit.weight

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout

class TagLayout : FlexboxLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var resId: Int = -1

    init {
        flexWrap = FlexWrap.WRAP
    }

    fun setItemLayout(resId: Int) {
        this.resId = resId
    }

    fun <T : ViewDataBinding> setData(data: ArrayList<String>) {
        removeAllViews()
        data.forEach { addText<T>(it) }
    }

    private fun <T : ViewDataBinding> addText(text: String) {
        val binding = DataBindingUtil.inflate<T>(LayoutInflater.from(context), resId, this, false)
        binding.root.setOnClickListener {
            if (::onTagClickListener.isInitialized)
                onTagClickListener(text)
        }
        binding.setVariable(BR.item, text)
        addView(binding.root)
    }

    fun setOnTagClickListener(listener: (tag: String) -> Unit) {
        onTagClickListener = listener
    }

    private lateinit var onTagClickListener: (tag: String) -> Unit

}