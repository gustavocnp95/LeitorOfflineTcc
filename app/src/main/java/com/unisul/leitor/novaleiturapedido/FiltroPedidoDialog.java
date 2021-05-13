
package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.unisul.leitor.BaseDialog;
import com.unisul.leitor.R;
import com.unisul.leitor.common.Event;
import com.unisul.leitor.database.AppDatabase;
import com.unisul.leitor.databinding.DialogFiltroInsertItensPedidoBinding;
import com.unisul.leitor.novaleiturapedido.model.PedidoFiltro;
import com.unisul.leitor.pedido.PedidoMapper;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FiltroPedidoDialog extends BaseDialog {
    @NonNull
    private final static String TAG = FiltroPedidoDialog.class.getSimpleName();
    @NonNull
    private final MutableLiveData<Event<PedidoFiltro>> pedidoFiltroMutableLiveData
            = new MutableLiveData<>();
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @Nullable
    private DialogFiltroInsertItensPedidoBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DialogFiltroInsertItensPedidoBinding.inflate(
                inflater, container, false);
        setupBtnConfirmar();
        startGetPedidos();
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(
                    Float.valueOf(
                            TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_PX,
                                    950,
                                    getResources().getDisplayMetrics()))
                            .intValue(),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @NonNull
    public LiveData<Event<PedidoFiltro>> getPedidoFiltroObservable() {
        return pedidoFiltroMutableLiveData;
    }

    @NonNull
    public DialogFiltroInsertItensPedidoBinding getBinding() {
        if (mBinding == null) {
            throw new IllegalStateException("O binding não pode ser acessado pois ainda é nulo!");
        }
        return mBinding;
    }

    private void startGetPedidos() {
        mDisposable.add(
                AppDatabase.getInstance(getContext())
                        .pedidoDao()
                        .getAllPedidosBySincronizado(false)
                        .map(PedidoMapper::toPedidoFiltro)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(this::setupSpinnerItens)
                        .doOnSubscribe(disposable -> showProgress(getBinding().progressBar))
                        .doFinally(() -> hideProgress(getBinding().progressBar))
                        .doOnError(this::logError)
                        .subscribe());
    }

    private void setupSpinnerItens(@NonNull final List<PedidoFiltro> pedidos) {
        getBinding().spinnerCodigoPedido.setOnItemSelectedListener(createOnItemSelectedListener());
        getBinding().spinnerCodigoPedido.setAdapter(
                new FiltroPedidoAdapter(
                        getContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        pedidos));
    }

    @NonNull
    private AdapterView.OnItemSelectedListener createOnItemSelectedListener() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NonNull final AdapterView<?> parent,
                                       @NonNull final View view,
                                       final int position,
                                       final long id) {
                final PedidoFiltro pedidoSelecionado
                        = (PedidoFiltro) getBinding().spinnerCodigoPedido.getSelectedItem();
                getBinding().textViewTipoItens.setText(pedidoSelecionado.getTipoItens());
                getBinding().textViewQuantidadeItens.setText(
                        String.valueOf(pedidoSelecionado.getQuantidadeItens()));
                getBinding().textViewCliente.setText(pedidoSelecionado.getClientePedido());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
    }

    private void setupBtnConfirmar() {
        getBinding().btnConfirmar.setOnClickListener(v -> {
            pedidoFiltroMutableLiveData.setValue(
                    new Event<>(
                            (PedidoFiltro) getBinding().spinnerCodigoPedido.getSelectedItem()));
            dismiss();
        });
    }
}
