package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.unisul.leitor.BaseActivity;
import com.unisul.leitor.R;
import com.unisul.leitor.databinding.ActivityInsertItensPedidoBinding;
import com.unisul.leitor.statuspedidos.StatusPedidosFragment;

public class InsertItensPedidoActivity extends BaseActivity {
    private ActivityInsertItensPedidoBinding mBinding;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_insert_itens_pedido);

        if (savedInstanceState == null) {
            addFragment(
                    new InsertItensPedidoFragment(),
                    R.id.fragContainer);
        }
    }

    public ActivityInsertItensPedidoBinding getBinding() {
        return mBinding;
    }
}
