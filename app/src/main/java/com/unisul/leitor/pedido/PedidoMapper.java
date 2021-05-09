package com.unisul.leitor.pedido;

import androidx.annotation.NonNull;

import com.unisul.leitor.pedido.db.PedidoEntity;
import com.unisul.leitor.statuspedidos.model.StatusPedidoListagem;

import java.util.List;
import java.util.stream.Collectors;

public final class PedidoMapper {
    public static List<StatusPedidoListagem> toStatusPedidoListagem(
            @NonNull final List<PedidoEntity> pedidosEntity) {
        return pedidosEntity.stream()
                .map(PedidoMapper::toStatusPedidoListagem)
                .collect(Collectors.toList());
    }

    public static StatusPedidoListagem toStatusPedidoListagem(
            @NonNull final PedidoEntity pedidoEntity) {
        return new StatusPedidoListagem(
                pedidoEntity.getId(),
                String.valueOf(pedidoEntity.getCodPedido()),
                String.valueOf(pedidoEntity.getQuantidadeItens()),
                String.valueOf(pedidoEntity.getStatus()));
    }
}