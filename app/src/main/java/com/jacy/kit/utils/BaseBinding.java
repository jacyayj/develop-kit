package com.jacy.kit.utils;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

public class BaseBinding {

    @BindingAdapter({"resId"})
    public static void displayResource(ImageView view, int resId) {
        if (resId != -1)
            view.setImageResource(resId);
    }

}
