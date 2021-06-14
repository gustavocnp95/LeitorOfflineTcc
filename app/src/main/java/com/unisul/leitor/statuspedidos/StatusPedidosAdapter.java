package com.unisul.leitor.statuspedidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unisul.leitor.common.animation.AnimationUtils;
import com.unisul.leitor.database.AppDatabase;
import com.unisul.leitor.databinding.ItemPedidosExpedidosBinding;
import com.unisul.leitor.pedido.PedidoRepositorio;
import com.unisul.leitor.statuspedidos.model.StatusPedidoListagem;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * Created on 2021-02-24
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public class StatusPedidosAdapter extends
        RecyclerView.Adapter<StatusPedidosAdapter.StatusPedidosHolder> {
    @NonNull
    private final List<StatusPedidoListagem> mPedidos;
    @NonNull
    private final CompositeDisposable mDisposable = new CompositeDisposable();
    @NonNull
    private final PedidoRepositorio mRepositorio = new PedidoRepositorio();

    public StatusPedidosAdapter(@NonNull final List<StatusPedidoListagem> pedidos) {
        this.mPedidos = pedidos;
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @NonNull
    @Override
    public StatusPedidosAdapter.StatusPedidosHolder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ItemPedidosExpedidosBinding itemBinding
                = ItemPedidosExpedidosBinding.inflate(layoutInflater, parent, false);
        return new StatusPedidosAdapter.StatusPedidosHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final StatusPedidosAdapter.StatusPedidosHolder holder,
                                 final int position) {
        final StatusPedidoListagem pedido = mPedidos.get(position);
        holder.bind(pedido);
    }

    @Override
    public int getItemCount() {
        return mPedidos.size();
    }

    public class StatusPedidosHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ItemPedidosExpedidosBinding mBinding;

        public StatusPedidosHolder(
                @NonNull final ItemPedidosExpedidosBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            setupBtnSincronizar();
        }

        public void bind(@NonNull final StatusPedidoListagem pedido) {
            mBinding.setItem(pedido);
            mBinding.executePendingBindings();
        }

        private void setupBtnSincronizar() {
            mBinding.buttonSync.setOnClickListener(v -> {
                if (!mBinding.getItem().getSincronizado()
                        && mBinding.getItem().isPreenchido()) {
                    mDisposable.add(
                            mRepositorio.enviarPedido(v.getContext(), mBinding.getItem().getIdPedido())
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .doOnSubscribe(disposable ->
                                            AnimationUtils.rotateUntilCleared(mBinding.buttonSync))
                                    .doOnComplete(() -> setPedidoSincronizado(v.getContext()))
                                    .doFinally(mBinding.buttonSync::clearAnimation)
                                    .subscribe(() -> {
                                    }, this::onError));
                }
            });
        }

        private void setPedidoSincronizado(@NonNull final Context context) {
            mDisposable.add(
                    AppDatabase.getInstance(context)
                            .pedidoDao()
                            .setPedidoSincronizado(mBinding.getItem().getIdPedido())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(() -> {
                                mBinding.getItem().setSincronizado(true);
                                Toast.makeText(
                                        context,
                                        "Enviado",
                                        Toast.LENGTH_SHORT)
                                        .show();
                            })
                            .doFinally(mBinding.buttonSync::clearAnimation)
                            .subscribe(() -> {
                            }, this::onError));
        }

        private void onError(@NonNull final Throwable throwable) {
            Toast.makeText(mBinding.buttonSync.getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}