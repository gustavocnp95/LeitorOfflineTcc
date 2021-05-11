package com.unisul.leitor;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class BaseDialog extends DialogFragment {
    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void showProgress(@NonNull final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress(@NonNull final ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }

    public void logError(@NonNull final Throwable throwable) {
        Log.d("ERRO NA OPERAÇÃO!", "Um erro ocorreu.", throwable);
    }
}
