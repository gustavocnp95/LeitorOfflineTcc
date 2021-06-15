package com.unisul.leitor.pedido.model;

/**
 * Created on 2021-06-14
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public class ItemPedidoLeitura {
    private String codigoBarras;

    public ItemPedidoLeitura(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(final String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
