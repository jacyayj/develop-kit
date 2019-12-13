package com.jacy.kit.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.jacy.develop.kit.R

class CircleProgressBar : View {
    private var isClockWise = true
    private var startAngle = 0f
    private var progressColor = 0
    private var progressBackgroundColor = 0
    private var centerColor = 0
    private var centerTextSize = 0f
    private var centerTextColor = 0
    private var strokeWidth = 0f

    private lateinit var centerPaint: Paint
    private lateinit var backGroundPaint: Paint
    private lateinit var progressPaint: Paint
    private lateinit var textPaint: Paint

    var progress = 0f
        set(value) {
            field = value
            invalidate()
        }

    var centerText = ""
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initStyle(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initStyle(attrs)
    }

    private fun initStyle(attrs: AttributeSet?) {
        val ts = context.theme.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar, 0, 0)
        isClockWise = ts.getBoolean(R.styleable.CircleProgressBar_cpb_isClockwise, true)
        startAngle = ts.getFloat(R.styleable.CircleProgressBar_cpb_startAngle, 0f)
        progress = ts.getFloat(R.styleable.CircleProgressBar_cpb_progress, 0f)
        progressColor = ts.getColor(R.styleable.CircleProgressBar_cpb_progressColor, Color.BLUE)
        progressBackgroundColor =
            ts.getColor(R.styleable.CircleProgressBar_cpb_progressBackgroundColor, Color.GRAY)
        centerText = ts.getString(R.styleable.CircleProgressBar_cpb_centerText) ?: ""
        centerColor = ts.getColor(R.styleable.CircleProgressBar_cpb_centerColor, Color.LTGRAY)
        centerTextSize = ts.getDimension(R.styleable.CircleProgressBar_cpb_centerTextSize, 30f)
        centerTextColor = ts.getColor(R.styleable.CircleProgressBar_cpb_centerTextColor, Color.BLACK)
        strokeWidth = ts.getDimension(R.styleable.CircleProgressBar_cpb_strokeWidth, 5f)
        centerPaint = Paint().apply {
            color = centerColor
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        backGroundPaint = Paint()
        backGroundPaint.color = progressBackgroundColor
        backGroundPaint.strokeWidth = strokeWidth
        backGroundPaint.style = Paint.Style.STROKE
        backGroundPaint.isAntiAlias = true

        progressPaint = Paint()
        progressPaint.color = progressColor
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = strokeWidth
        progressPaint.isAntiAlias = true

        textPaint = Paint().apply {
            color = centerTextColor
            textSize = centerTextSize
            style = Paint.Style.FILL
            textAlign = Paint.Align.CENTER
            isAntiAlias = true
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.drawCircle(
            width / 2f,
            height / 2f,
            (width - strokeWidth) / 2f,
            backGroundPaint
        )
        canvas?.drawCircle(
            width / 2f,
            height / 2f,
            (width - strokeWidth * 2) / 2f,
            centerPaint
        )
        if (progress != 0f)
            canvas?.drawArc(
                RectF(
                    strokeWidth / 2,
                    strokeWidth / 2,
                    width - strokeWidth / 2,
                    height - strokeWidth / 2
                ),
                startAngle,
                (if (isClockWise) 360f else -360f) * progress / 100,
                false,
                progressPaint
            )

        if (centerText.isNotEmpty()) {
            val y =
                height / 2 + (textPaint.fontMetrics.bottom - textPaint.fontMetrics.top) / 2 - textPaint.fontMetrics.bottom
            canvas?.drawText(centerText, width / 2f, y, textPaint)
        }
    }
}