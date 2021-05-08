package com.unisul.leitor.statuspedidos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unisul.leitor.databinding.ItemPedidosExpedidosBinding;
import com.unisul.leitor.pedido.db.PedidoEntity;

import java.util.List;


/**
 * Created on 2021-02-24
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public class StatusPedidosAdapter extends
        RecyclerView.Adapter<StatusPedidosAdapter.StatusPedidosHolder> {
    @NonNull
    private final List<PedidoEntity> mPedidos;

    public StatusPedidosAdapter(@NonNull final List<PedidoEntity> pedidos) {
        this.mPedidos = pedidos;
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
        final PedidoEntity pedidoEntity = mPedidos.get(position);
        holder.bind(pedidoEntity);
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
        }

        public void bind(@NonNull final PedidoEntity pedidoEntity) {
            mBinding.setItem(pedidoEntity);
            mBinding.executePendingBindings();
        }
    }
}