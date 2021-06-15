package com.unisul.leitor.pedido;

import androidx.annotation.NonNull;

import com.unisul.leitor.novaleiturapedido.model.PedidoFiltro;
import com.unisul.leitor.pedido.db.PedidoEntity;
import com.unisul.leitor.pedido.itempedido.db.ItemPedidoEntity;
import com.unisul.leitor.pedido.model.ItemPedidoLeitura;
import com.unisul.leitor.pedido.model.PedidoLeitura;
import com.unisul.leitor.pedido.model.PedidoListagem;
import com.unisul.leitor.statuspedidos.model.StatusPedidoListagem;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public final class PedidoMapper {

    public static List<PedidoEntity> toPedidoEntity(@NonNull final List<PedidoListagem> pedidos) {
        return pedidos.stream().map(PedidoMapper::toPedidoEntity).collect(Collectors.toList());
    }

    public static PedidoEntity toPedidoEntity(@NonNull final PedidoListagem pedido) {
        return new PedidoEntity(
                pedido.getCodigoPedido(),
                pedido.getTipoItens(),
                pedido.getQuantidadeItens(),
                pedido.getNomeCliente(),
                pedido.getStatus(),
                false);
    }

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
                String.valueOf(pedidoEntity.getStatus()),
                pedidoEntity.isSincronizado());
    }

    public static List<PedidoFiltro> toPedidoFiltro(
            @NonNull final List<PedidoEntity> pedidosEntity) {
        return pedidosEntity.stream()
                .map(PedidoMapper::toPedidoFiltro)
                .collect(Collectors.toList());
    }

    public static PedidoFiltro toPedidoFiltro(
            @NonNull final PedidoEntity pedidoEntity) {
        return new PedidoFiltro(
                pedidoEntity.getId(),
                pedidoEntity.getCodPedido(),
                pedidoEntity.getQuantidadeItens(),
                pedidoEntity.getTipoItens(),
                pedidoEntity.getNomeCliente());
    }

    public static PedidoLeitura toPedidoLeitura(final long codPedido, @NonNull final List<ItemPedidoEntity> itensPedido) {
        if (itensPedido.isEmpty()) {
            throw new IllegalStateException("Lista nao pode ser vazia!");
        }
        return new PedidoLeitura(
                codPedido,
                itensPedido.stream()
                        .map(PedidoMapper::toItemPedidoLeitura)
                        .collect(Collectors.toList()));
    }

    public static ItemPedidoLeitura toItemPedidoLeitura(@NonNull final ItemPedidoEntity itemPedidoEntity) {
        return new ItemPedidoLeitura(itemPedidoEntity.getCodigoBarras());
    }

}
