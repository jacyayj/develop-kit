package com.jacy.develop.kit.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class BaseBinding {

    @BindingAdapter({"resId"})
    public static void displayResource(ImageView view, int resId) {
        if (resId != -1)
            view.setImageResource(resId);
    }

}
