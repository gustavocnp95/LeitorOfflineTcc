package com.unisul.leitor.statuspedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unisul.leitor.BaseFragment;
import com.unisul.leitor.R;
import com.unisul.leitor.database.AppDatabase;
import com.unisul.leitor.databinding.FragmentStatusPedidosBinding;
import com.unisul.leitor.pedido.PedidoMapper;
import com.unisul.leitor.statuspedidos.model.StatusPedidoListagem;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StatusPedidosFragment extends BaseFragment {
    @Nullable
    private FragmentStatusPedidosBinding mBinding;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_status_pedidos,
                container,
                false);
        setupSwipeToRefresh();
        setupRecyclerView();
        return getBinding().getRoot();
    }

    @NonNull
    public FragmentStatusPedidosBinding getBinding() {
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

    private void setupSwipeToRefresh() {
        getBinding().layoutSwipeToRefresh.setOnRefreshListener(this::startGetRecyclerViewItens);
    }

    private void setupRecyclerView() {
        getBinding().recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        startGetRecyclerViewItens();
    }

    private void recreateRecyclerViewItens(@NonNull final List<StatusPedidoListagem> pedidos) {
        if (pedidos.isEmpty()) {
            getBinding().textViewNenhumPedido.setVisibility(View.VISIBLE);
            return;
        }
        getBinding().textViewNenhumPedido.setVisibility(View.GONE);
        getBinding().recycler.setAdapter(new StatusPedidosAdapter(pedidos));
    }

    private void startGetRecyclerViewItens() {
        mDisposable.add(
                AppDatabase.getInstance(getContext())
                        .pedidoDao()
                        .getAllPedidos()
                        .map(PedidoMapper::toStatusPedidoListagem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSuccess(this::recreateRecyclerViewItens)
                        .doOnSubscribe(disposable -> showProgress(mBinding.progressBar))
                        .doFinally(this::hideProgresses)
                        .doOnError(this::logError)
                        .subscribe());
    }

    private void hideProgresses() {
        hideProgress(getBinding().progressBar);
        getBinding().layoutSwipeToRefresh.setRefreshing(false);
    }
}
