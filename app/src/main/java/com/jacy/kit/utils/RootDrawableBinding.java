package com.jacy.kit.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class RootDrawableBinding {

    @BindingAdapter({"resId"})
    public static void displayResource(ImageView view, int resId) {
        if (resId != -1)
            view.setImageResource(resId);
    }
    @BindingAdapter({"background"})
    public static void setBackground(View view, int resId) {
        if (resId != -1)
            view.setBackgroundResource(resId);
    }

    @BindingAdapter({"drawableTop"})
    public static void setDrawableTop(TextView view, int resId) {
        if (resId != -1)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, resId, 0, 0);
        else
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
    }

    @BindingAdapter({"drawableBottom"})
    public static void setDrawableBottom(TextView view, int resId) {
        if (resId != -1)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, resId);
        else
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
    }

    @BindingAdapter({"drawableStart"})
    public static void setDrawableStart(TextView view, int resId) {
        if (resId != -1)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(resId, 0, 0, 0);
        else
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
    }

    @BindingAdapter({"drawableEnd"})
    public static void setDrawableEnd(TextView view, int resId) {
        if (resId != -1)
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, resId, 0);
        else
            view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
    }

}
