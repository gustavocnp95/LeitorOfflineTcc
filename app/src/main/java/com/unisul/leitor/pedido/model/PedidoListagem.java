package com.unisul.leitor.pedido.model;

import androidx.annotation.NonNull;

/**
 * Created on 2021-06-13
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public class PedidoListagem {
    private long codigoPedido;
    private String tipoItens;
    private long quantidadeItens;
    private String nomeCliente;
    private String status;

    public PedidoListagem() {
    }

    public long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    @NonNull
    public String getTipoItens() {
        return tipoItens;
    }

    public void setTipoItens(@NonNull String tipoItens) {
        this.tipoItens = tipoItens;
    }

    public long getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(long quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    @NonNull
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(@NonNull String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @NonNull
    public String getStatus() {
        return status;
    }

    public void setStatus(@NonNull String status) {
        this.status = status;
    }
}
