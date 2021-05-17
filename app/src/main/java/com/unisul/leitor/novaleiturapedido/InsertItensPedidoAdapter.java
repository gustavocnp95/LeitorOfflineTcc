package com.unisul.leitor.novaleiturapedido;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unisul.leitor.databinding.ItemInsertItensPedidoBinding;
import com.unisul.leitor.novaleiturapedido.model.ItemPedidoToInsert;

import java.util.ArrayList;
import java.util.List;

public class InsertItensPedidoAdapter extends
        RecyclerView.Adapter<InsertItensPedidoAdapter.InsertItensPedidoHolder> {
    @NonNull
    private final List<ItemPedidoToInsert> mPedidos = new ArrayList<>();

    public InsertItensPedidoAdapter() {
    }

    @NonNull
    @Override
    public InsertItensPedidoAdapter.InsertItensPedidoHolder onCreateViewHolder(
            @NonNull final ViewGroup parent,
            final int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ItemInsertItensPedidoBinding itemBinding
                = ItemInsertItensPedidoBinding.inflate(layoutInflater, parent, false);
        return new InsertItensPedidoHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(
            @NonNull final InsertItensPedidoAdapter.InsertItensPedidoHolder holder,
            final int position) {
        final ItemPedidoToInsert pedido = mPedidos.get(position);
        holder.bind(pedido);
    }

    @Override
    public int getItemCount() {
        return mPedidos.size();
    }

    public int addItem(@NonNull final ItemPedidoToInsert itemPedido) {
        final int newPosition = mPedidos.size() + 1;
        mPedidos.add(itemPedido);
        notifyItemInserted(newPosition);
        return newPosition;
    }

    @NonNull
    public List<ItemPedidoToInsert> getPedidos() {
        return this.mPedidos;
    }

    public static class InsertItensPedidoHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ItemInsertItensPedidoBinding mBinding;

        public InsertItensPedidoHolder(
                @NonNull final ItemInsertItensPedidoBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public void bind(@NonNull final ItemPedidoToInsert itemPedido) {
            mBinding.setItem(itemPedido);
            mBinding.executePendingBindings();
        }
    }
}
