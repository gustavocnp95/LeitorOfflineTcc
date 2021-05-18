package com.unisul.leitor.common;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.adapters.Converters;

public class ImageViewBindingAdapter {
    @BindingAdapter("app:tint")
    public static void setTintCompat(ImageView imageView, @ColorInt int color) {
        imageView.setImageTintList(Converters.convertColorToColorStateList(color));
    }

    private static Drawable tintDrawable(@NonNull Drawable drawable, @ColorInt int tintInt) {
        drawable = DrawableCompat.wrap(DrawableCompat.unwrap(drawable).mutate());
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        DrawableCompat.setTint(drawable, tintInt);
        return drawable;
    }
}
