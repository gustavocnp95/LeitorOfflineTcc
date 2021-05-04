package com.unisul.leitor.mainmenu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.unisul.leitor.BaseActivity;
import com.unisul.leitor.R;
import com.unisul.leitor.databinding.ActivityMainMenuBinding;

public class MainMenuActivity extends BaseActivity {
    private ActivityMainMenuBinding mBinding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);

        if (savedInstanceState == null) {
            addFragment(
                    new MainMenuFragment(),
                    R.id.fragContainer);
        }
    }
}