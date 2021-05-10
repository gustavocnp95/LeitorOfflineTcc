package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unisul.leitor.BaseDialog;
import com.unisul.leitor.databinding.DialogFiltroInsertItensPedidoBinding;

public class FiltroPedidoDialog extends BaseDialog {
    @NonNull
    private final static String TAG = FiltroPedidoDialog.class.getSimpleName();
    @Nullable
    private DialogFiltroInsertItensPedidoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DialogFiltroInsertItensPedidoBinding.inflate(
                inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                Float.valueOf(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                        950,
                        getResources().getDisplayMetrics())).intValue(), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @NonNull
    public DialogFiltroInsertItensPedidoBinding getBinding() {
        if (mBinding == null) {
            throw new IllegalStateException("O binding não pode ser acessado pois ainda é nulo!");
        }
        return mBinding;
    }
}
