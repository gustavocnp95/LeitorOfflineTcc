package com.unisul.leitor.statuspedidos;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.unisul.leitor.BaseActivity;
import com.unisul.leitor.R;
import com.unisul.leitor.databinding.ActivityStatusPedidosBinding;

public class StatusPedidosActivity extends BaseActivity {
    private ActivityStatusPedidosBinding mBinding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_status_pedidos);

        if (savedInstanceState == null) {
            addFragment(
                    new StatusPedidosFragment(),
                    R.id.fragContainer);
        }
    }
}