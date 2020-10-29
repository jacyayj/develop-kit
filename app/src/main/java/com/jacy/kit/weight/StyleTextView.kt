package com.jacy.kit.weight

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.jacy.develop.kit.R

class StyleTextView : AppCompatTextView {

    private var radius = 0f
    private var strokeWidth = 0f
    private var strokeColor = 0f

    private lateinit var backgroundDrawable: Bitmap
    private lateinit var strokePaint: Paint
    private lateinit var backgroundPaint: Paint

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initStyle(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initStyle(attrs)
    }

    private fun initStyle(attrs: AttributeSet?) {
        val ts = context.theme.obtainStyledAttributes(attrs, R.styleable.StyleTextView, 0, 0)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}