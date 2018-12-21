package com.jacy.kit.weight

/**
 * 介绍：这个是自定义的布局，自定义布局可以继承各种常见布局。自定义布局有键盘状态改变监听器，可以通过注册监听器来监听软键盘状态。
 */

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class KeyboardLayout : LinearLayout {
    private var mHasInit: Boolean = false
    private var mHasKeybord: Boolean = false
    private var mHeight: Int = 0
    private var mListener: onKeyboardChangeListener? = null

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    /**
     * 设置键盘状态监听器
     */
    fun setOnkbdStateListener(listener: onKeyboardChangeListener) {
        mListener = listener
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (!mHasInit) {
            mHasInit = true
            mHeight = b//获取底部高度
            if (mListener != null) {//初始状态
                mListener!!.onKeyBoardStateChange(KEYBOARD_STATE_INIT)
            }
        } else {
            mHeight = if (mHeight < b) b else mHeight
        }
        if (mHasInit && mHeight > b) {//大于则表示布局本遮挡或顶起
            mHasKeybord = true
            if (mListener != null) {//弹出
                mListener!!.onKeyBoardStateChange(KEYBOARD_STATE_SHOW)
            }
            Log.w(TAG, "show keyboard.......")
        }
        if (mHasInit && mHasKeybord && mHeight == b) {//布局曾被遮挡或顶起，且回到了初始高度
            mHasKeybord = false
            if (mListener != null) {//收起
                mListener!!.onKeyBoardStateChange(KEYBOARD_STATE_HIDE)
            }
            Log.w(TAG, "hide keyboard.......")
        }
    }

    interface onKeyboardChangeListener {
        fun onKeyBoardStateChange(state: Int)
    }

    companion object {
        private val TAG = KeyboardLayout::class.java.simpleName
        const val KEYBOARD_STATE_SHOW = -3//软键盘弹起
        const val KEYBOARD_STATE_HIDE = -2//软键盘隐藏
        const val KEYBOARD_STATE_INIT = -1//初始
    }
}