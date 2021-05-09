package com.unisul.leitor;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    public void showProgress(@NonNull final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress(@NonNull final ProgressBar progressBar) {
        progressBar.setVisibility(View.GONE);
    }
}
