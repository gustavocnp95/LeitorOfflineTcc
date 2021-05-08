package com.unisul.leitor.statuspedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unisul.leitor.R;
import com.unisul.leitor.databinding.FragmentStatusPedidosBinding;
import com.unisul.leitor.pedido.db.PedidoEntity;

import java.util.List;

public class StatusPedidosFragment extends Fragment {
    @Nullable
    private FragmentStatusPedidosBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_status_pedidos,
                container,
                false);
        setupRecyclerView();
        return mBinding.getRoot();
    }

    private void setupRecyclerView() {
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recycler.setAdapter(
                new StatusPedidosAdapter(
                        List.of(
                                new PedidoEntity(
                                        232,
                                        "Tipo_teste",
                                        10,
                                        "Cliente teste",
                                        "Pendente"))));
    }
}
