package com.jacy.develop.kit.weight

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView

open class VerticalRecyclerView : SwipeMenuRecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}
