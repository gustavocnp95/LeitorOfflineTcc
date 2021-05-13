package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.unisul.leitor.BaseFragment;
import com.unisul.leitor.R;
import com.unisul.leitor.common.Event;
import com.unisul.leitor.databinding.FragmentInsertItensPedidoBinding;
import com.unisul.leitor.novaleiturapedido.model.PedidoFiltro;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class InsertItensPedidoFragment extends BaseFragment {
    @Nullable
    private FragmentInsertItensPedidoBinding mBinding;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @NonNull
    private final Observer<Event<PedidoFiltro>> mPedidoFiltroObserver = createPedidoFiltroObserver();

    private Observer<Event<PedidoFiltro>> createPedidoFiltroObserver() {
        return pedidoFiltroEvent ->
                pedidoFiltroEvent
                        .getContentIfNotHandled()
                        .ifPresent(this::setInitialInfos);
    }

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

    @Override
    public void onStart() {
        super.onStart();
        FiltroPedidoDialog dialog = new FiltroPedidoDialog();
        dialog.getPedidoFiltroObservable().observe(getViewLifecycleOwner(), mPedidoFiltroObserver);
        dialog.show(getParentFragmentManager(), dialog.getTag());
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

    private void setInitialInfos(@NonNull final PedidoFiltro pedidoFiltro) {
        getBinding().textViewCodigoPedido.setText(String.valueOf(pedidoFiltro.getCodPedido()));
        getBinding().textViewTipoItem.setText(pedidoFiltro.getTipoItens());
        getBinding().textViewQuantidadeItens.setText(
                String.valueOf(pedidoFiltro.getQuantidadeItens()));
        getBinding().textViewCliente.setText(pedidoFiltro.getClientePedido());
        ((InsertItensPedidoActivity) getActivity()).getBinding().textViewSubtitle.setText(
                getString(R.string.leitura_pedido_subtitle, pedidoFiltro.getCodPedido()));
    }
}
