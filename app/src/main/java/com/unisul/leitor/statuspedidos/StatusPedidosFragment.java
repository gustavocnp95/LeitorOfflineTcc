package com.unisul.leitor.statuspedidos;

import android.content.Intent;
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
import com.unisul.leitor.common.AndroidUtils;
import com.unisul.leitor.database.AppDatabase;
import com.unisul.leitor.databinding.FragmentStatusPedidosBinding;
import com.unisul.leitor.novaleiturapedido.InsertItensPedidoActivity;
import com.unisul.leitor.pedido.PedidoMapper;
import com.unisul.leitor.pedido.PedidoRepositorio;
import com.unisul.leitor.pedido.db.PedidoEntity;
import com.unisul.leitor.pedido.model.PedidoListagem;
import com.unisul.leitor.statuspedidos.model.StatusPedidoListagem;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StatusPedidosFragment extends BaseFragment {
    @Nullable
    private FragmentStatusPedidosBinding mBinding;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @NonNull
    private final PedidoRepositorio repositorio = new PedidoRepositorio();

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
        setupBtnNovaLeitura();
        return getBinding().getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        startGetRecyclerViewItens();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @NonNull
    public FragmentStatusPedidosBinding getBinding() {
        if (mBinding == null) {
            throw new IllegalStateException("O binding não pode ser acessado porque ainda é nulo!");
        }
        return mBinding;
    }

    private void setupSwipeToRefresh() {
        getBinding().layoutSwipeToRefresh.setOnRefreshListener(this::startGetRecyclerViewItens);
    }

    private void setupRecyclerView() {
        getBinding().recycler.setLayoutManager(new LinearLayoutManager(getContext()));
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
        if (AndroidUtils.isNetworkAvailable(getContext())) {
            if (getContext() != null) {
                mDisposable.add(
                        AppDatabase.getInstance(getContext())
                                .pedidoDao()
                                .deletePendentes()
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnSubscribe(disposable -> showProgress(getBinding().progressBar))
                                .subscribe(this::onSuccessDeletePedidosPendentes, this::onError)
                );
            }
        } else {
            getAllPedidosLocal();
        }
    }

    private void hideProgresses() {
        hideProgress(getBinding().progressBar);
        getBinding().layoutSwipeToRefresh.setRefreshing(false);
    }

    private void setupBtnNovaLeitura() {
        getBinding().btnNovoPedido.setOnClickListener(v -> startActivityNovaLeituraPedido());
    }

    private void startActivityNovaLeituraPedido() {
        startActivity(new Intent(getActivity(), InsertItensPedidoActivity.class));
    }

    private void onSuccessDeletePedidosPendentes() {
        if (getContext() != null) {
            mDisposable.add(
                    repositorio
                            .syncPedidosPendentes(getContext())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(this::onSuccessSyncPedidosPendentes, this::onError)
            );
        }
    }

    private void onSuccessSyncPedidosPendentes(@NonNull final List<PedidoListagem> pedidos) {
        mDisposable.add(
                AppDatabase.getInstance(getContext())
                        .pedidoDao()
                        .getAllPedidosPreenchidosAndNotSync()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                pedidoEntities -> onSuccessGetAllPedidosPreenchidosAndNotSync(pedidos, pedidoEntities),
                                this::onError));


    }

    private void onSuccessGetAllPedidosPreenchidosAndNotSync(@NonNull final List<PedidoListagem> pedidosRecebidos,
                                                             @NonNull final List<PedidoEntity> pedidosExistentes) {
        mDisposable.add(
                AppDatabase.getInstance(getContext())
                        .pedidoDao()
                        .insertPedidos(PedidoMapper.toPedidoEntity(
                                pedidosRecebidos.stream()
                                        .filter(pedidoListagem ->
                                                pedidosExistentes.stream()
                                                        .noneMatch(pedidoEntity ->
                                                                pedidoEntity.getCodPedido() == pedidoListagem.getCodigoPedido()))
                                        .collect(Collectors.toList())))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::onSuccessInsertPedidos, this::onError));
    }

    private void onSuccessInsertPedidos() {
        getAllPedidosLocal();
    }

    private void getAllPedidosLocal() {
        mDisposable.add(
                AppDatabase.getInstance(getContext())
                        .pedidoDao()
                        .getAllPedidos()
                        .map(PedidoMapper::toStatusPedidoListagem)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> showProgress(getBinding().progressBar))
                        .doFinally(this::hideProgresses)
                        .subscribe(this::recreateRecyclerViewItens, this::onError));
    }

    private void onError(@NonNull final Throwable throwable) {
        hideProgresses();
        logError(throwable);
    }
}
