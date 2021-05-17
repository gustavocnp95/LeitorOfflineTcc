package com.unisul.leitor.pedido.itempedido;

import androidx.annotation.NonNull;

import com.unisul.leitor.novaleiturapedido.model.ItemPedidoToInsert;
import com.unisul.leitor.pedido.itempedido.db.ItemPedidoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ItemPedidoMapper {
    public static List<ItemPedidoEntity> toEntity(final long idPedido,
                                            @NonNull final List<ItemPedidoToInsert> pedidos) {
        return pedidos.stream()
                .map(itemPedidoToInsert -> toEntity(idPedido, itemPedidoToInsert))
                .collect(Collectors.toList());
    }

    private static ItemPedidoEntity toEntity(final long idPedido,
                                      @NonNull final ItemPedidoToInsert itemPedidoToInsert) {
        return new ItemPedidoEntity(
                idPedido,
                itemPedidoToInsert.getCodBarras());
    }
}
