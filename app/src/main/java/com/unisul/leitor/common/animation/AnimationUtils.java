package com.unisul.leitor.common.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;

public class AnimationUtils {
    public static void rotateUntilCleared(@NonNull final View view) {
        RotateAnimation rotate = new RotateAnimation(
                360,
                0,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        rotate.setDuration(900);
        rotate.setRepeatCount(Animation.INFINITE);
        view.startAnimation(rotate);
    }
}
