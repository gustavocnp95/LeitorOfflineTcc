package com.unisul.leitor.mainmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.unisul.leitor.R;
import com.unisul.leitor.databinding.FragmentMainMenuBinding;

public class MainMenuFragment extends Fragment {
    @Nullable
    private FragmentMainMenuBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_main_menu,
                container,
                false);
        return mBinding.getRoot();
    }
}
