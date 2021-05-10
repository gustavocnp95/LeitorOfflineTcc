package com.unisul.leitor.novaleiturapedido.model;

public final class PedidoFiltro {
    private final long idPedido;
    private final long codPedido;
    private final long quantidadeItens;
    private final String tipoItens;
    private final String clientePedido;

    public PedidoFiltro(final long idPedido,
                        final long codPedido,
                        final long quantidadeItens,
                        final String tipoItens,
                        final String clientePedido) {
        this.idPedido = idPedido;
        this.codPedido = codPedido;
        this.quantidadeItens = quantidadeItens;
        this.tipoItens = tipoItens;
        this.clientePedido = clientePedido;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public long getQuantidadeItens() {
        return quantidadeItens;
    }

    public String getTipoItens() {
        return tipoItens;
    }

    public String getClientePedido() {
        return clientePedido;
    }
}
