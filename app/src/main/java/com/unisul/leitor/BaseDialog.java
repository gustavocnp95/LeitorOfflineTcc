package com.unisul.leitor;

import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

public class BaseDialog extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
}
