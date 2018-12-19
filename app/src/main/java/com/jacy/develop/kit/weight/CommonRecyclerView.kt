package com.jacy.develop.kit.weight

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import com.gc.materialdesign.utils.Utils
import com.jacy.develop.kit.R
import com.jacy.develop.kit.config.mgetColor
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView
import com.yanzhenjie.recyclerview.swipe.widget.DefaultItemDecoration

open class CommonRecyclerView : SwipeMenuRecyclerView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)


    fun initVertical(dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                0,
                Utils.dpToPx(dividerSize, resources)
            )
        )
    }

    fun initHorizontal(dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                Utils.dpToPx(dividerSize, resources),
                0
            )
        )
    }

    fun initGrid(lineCount: Int, dividerSize: Float = 0.5f, dividerColor: Int = R.color.colorDefaultDivider) {
        layoutManager = GridLayoutManager(context, lineCount)
        addItemDecoration(
            DefaultItemDecoration(
                context.mgetColor(dividerColor),
                Utils.dpToPx(dividerSize, resources),
                Utils.dpToPx(dividerSize, resources)
            )
        )
    }
}
