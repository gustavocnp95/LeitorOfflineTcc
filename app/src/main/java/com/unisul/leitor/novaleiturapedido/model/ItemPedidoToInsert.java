package com.unisul.leitor.novaleiturapedido.model;

public class ItemPedidoToInsert {
    private final String codBarras;

    public ItemPedidoToInsert(String codBarras) {
        this.codBarras = codBarras;
    }

    public String getCodBarras() {
        return codBarras;
    }
}
