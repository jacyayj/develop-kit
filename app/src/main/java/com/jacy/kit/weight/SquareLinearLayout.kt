package com.jacy.kit.weight

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.LinearLayout

class SquareLinearLayout : LinearLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}