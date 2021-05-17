package com.unisul.leitor.novaleiturapedido;

import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unisul.leitor.BaseFragment;
import com.unisul.leitor.R;
import com.unisul.leitor.common.Event;
import com.unisul.leitor.database.AppDatabase;
import com.unisul.leitor.databinding.FragmentInsertItensPedidoBinding;
import com.unisul.leitor.novaleiturapedido.model.ItemPedidoToInsert;
import com.unisul.leitor.novaleiturapedido.model.PedidoFiltro;
import com.unisul.leitor.pedido.itempedido.ItemPedidoMapper;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertItensPedidoFragment extends BaseFragment {
    @Nullable
    private FragmentInsertItensPedidoBinding mBinding;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @NonNull
    private final Observer<Event<PedidoFiltro>> mPedidoFiltroObserver
            = createPedidoFiltroObserver();
    @Nullable
    private PedidoFiltro pedidoSelecionado;

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
        setupRecyclerView();
        setupInputNovoItem();
        setupBtnSalvar();
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
        mBinding = null;
    }

    private void setInitialInfos(@NonNull final PedidoFiltro pedidoFiltro) {
        pedidoSelecionado = pedidoFiltro;
        getBinding().textViewCodigoPedido.setText(String.valueOf(pedidoFiltro.getCodPedido()));
        getBinding().textViewTipoItem.setText(pedidoFiltro.getTipoItens());
        getBinding().textViewQuantidadeItens.setText(
                String.valueOf(pedidoFiltro.getQuantidadeItens()));
        getBinding().textViewCliente.setText(pedidoFiltro.getClientePedido());
        if (getActivity() != null) {
            ((InsertItensPedidoActivity) getActivity()).getBinding().textViewSubtitle.setText(
                    getString(R.string.leitura_pedido_subtitle, pedidoFiltro.getCodPedido()));
        }
    }

    private void setupRecyclerView() {
        getBinding().recyclerItens.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().recyclerItens.setAdapter(new InsertItensPedidoAdapter());
    }

    private void setupInputNovoItem() {
        getBinding().textViewNovoCodigoBarras.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                final int newPosition =
                        (getRecyclerViewAdapter())
                                .addItem(new ItemPedidoToInsert(
                                        getBinding().textViewNovoCodigoBarras.getText().toString()));
                getBinding().textViewNovoCodigoBarras.setText("");
                getBinding().recyclerItens.smoothScrollToPosition(newPosition);
                return true;
            }
            return false;
        });
        getBinding().textViewNovoCodigoBarras.setInputType(InputType.TYPE_NULL);
    }

    private void setupBtnSalvar() {
        getBinding().btnSalvar.setOnClickListener(this::salvarItens);
    }

    private void salvarItens(@NonNull final View view) {
        if (!getRecyclerViewAdapter().getPedidos().isEmpty()) {
            insertInDatabase(getItensPedidosFromAdapter());
        } else {
            Toast.makeText(
                    getContext(), "É necessário ler ao menos um item!", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @NonNull
    private List<ItemPedidoToInsert> getItensPedidosFromAdapter() {
        return getRecyclerViewAdapter().getPedidos();
    }

    @NonNull
    private InsertItensPedidoAdapter getRecyclerViewAdapter() {
        if (getBinding().recyclerItens.getAdapter() != null) {
            return (InsertItensPedidoAdapter) getBinding().recyclerItens.getAdapter();
        }
        throw new IllegalStateException("O adapter da recycler não pode ser acessado pois é nulo.");
    }

    private void insertInDatabase(@NonNull final List<ItemPedidoToInsert> itensPedido) {
        if (pedidoSelecionado != null) {
            mDisposable.add(
                    AppDatabase.getInstance(getContext())
                            .itemPedidoDao()
                            .insertItemPedido(ItemPedidoMapper.toEntity(
                                    pedidoSelecionado.getIdPedido(), itensPedido))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(this::onCompleteInsert)
                            .doOnSubscribe(this::showProgress)
                            .doFinally(this::hideProgress)
                            .doOnError(this::logError)
                            .subscribe());
        }
    }

    private void onCompleteInsert() {
        if (pedidoSelecionado != null) {
            mDisposable.add(
                    AppDatabase.getInstance(getContext()).pedidoDao()
                            .setPedidoLido(pedidoSelecionado.getIdPedido())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(this::onCompleteUpdate)
                            .doOnSubscribe(this::showProgress)
                            .doFinally(this::hideProgress)
                            .doOnError(this::logError)
                            .subscribe());
        }
    }

    private void onCompleteUpdate() {
        Toast.makeText(getContext(), "Sucesso ao salvar os itens!", Toast.LENGTH_LONG).show();
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    private void showProgress(@NonNull final Disposable disposable) {
        getBinding().layoutMain.setVisibility(View.INVISIBLE);
        showProgress(getBinding().progressBar);
    }

    private void hideProgress() {
        hideProgress(getBinding().progressBar);
        getBinding().layoutMain.setVisibility(View.VISIBLE);
    }
}
