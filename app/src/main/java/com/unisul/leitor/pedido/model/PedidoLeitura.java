package com.unisul.leitor.pedido.model;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Created on 2021-06-14
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public class PedidoLeitura {
    @NonNull
    private final Long codigoPedido;
    @NonNull
    private final List<ItemPedidoLeitura> itensPedido;

    public PedidoLeitura(@NonNull final Long codigoPedido, @NonNull final List<ItemPedidoLeitura> itensPedido) {
        this.codigoPedido = codigoPedido;
        this.itensPedido = itensPedido;
    }

    @NonNull
    public Long getCodigoPedido() {
        return codigoPedido;
    }

    @NonNull
    public List<ItemPedidoLeitura> getItensPedido() {
        return itensPedido;
    }
}
