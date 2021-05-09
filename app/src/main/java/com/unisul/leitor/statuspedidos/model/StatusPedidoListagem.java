package com.unisul.leitor.statuspedidos.model;

import androidx.annotation.NonNull;

public class StatusPedidoListagem {
    private final long idPedido;
    @NonNull
    private final String codPedido;
    @NonNull
    private final String quantidadeItens;
    @NonNull
    private final String statusPedido;

    public StatusPedidoListagem(final long idPedido,
                                @NonNull final String codPedido,
                                @NonNull final String quantidadeItens,
                                @NonNull final String statusPedido) {
        this.idPedido = idPedido;
        this.codPedido = codPedido;
        this.quantidadeItens = quantidadeItens;
        this.statusPedido = statusPedido;
    }

    public long getIdPedido() {
        return idPedido;
    }

    @NonNull
    public String getCodPedido() {
        return codPedido;
    }

    @NonNull
    public String getQuantidadeItens() {
        return quantidadeItens;
    }

    @NonNull
    public String getStatusPedido() {
        return statusPedido;
    }
}