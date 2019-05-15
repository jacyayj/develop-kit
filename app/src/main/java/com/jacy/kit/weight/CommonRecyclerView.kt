package com.jacy.kit.weight

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.AttributeSet
import com.jacy.develop.kit.R
import com.jacy.kit.config.mgetColor
import com.scwang.smartrefresh.layout.util.DensityUtil
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration

open class CommonRecyclerView : SwipeMenuRecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


    fun initVertical(dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                0,
                DensityUtil.dp2px(dividerSize)
            )
        )
    }

    fun initHorizontal(dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            context,
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
            false
        )
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                DensityUtil.dp2px(dividerSize),
                0
            )
        )
    }

    fun initGrid(lineCount: Int, dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, lineCount)
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                DensityUtil.dp2px(dividerSize),
                DensityUtil.dp2px(dividerSize)
            )
        )
    }
}
