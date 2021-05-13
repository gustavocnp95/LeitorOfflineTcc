package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.unisul.leitor.BaseFragment;
import com.unisul.leitor.R;
import com.unisul.leitor.databinding.FragmentInsertItensPedidoBinding;
import com.unisul.leitor.databinding.FragmentStatusPedidosBinding;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class InsertItensPedidoFragment extends BaseFragment {
    @Nullable
    private FragmentInsertItensPedidoBinding mBinding;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_insert_itens_pedido,
                container,
                false);
        return getBinding().getRoot();
    }

    @NonNull
    public FragmentInsertItensPedidoBinding getBinding() {
        if (mBinding == null) {
            throw new IllegalStateException("O binding não pode ser acessado porque ainda é nulo!");
        }
        return mBinding;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
