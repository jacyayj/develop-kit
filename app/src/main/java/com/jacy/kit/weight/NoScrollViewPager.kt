package com.jacy.kit.weight

import android.content.Context
import androidx.viewpager.widget.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Administrator on 2017/6/25 0025.
 */

class NoScrollViewPager : androidx.viewpager.widget.ViewPager {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return false
    }


    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return false
    }
}
