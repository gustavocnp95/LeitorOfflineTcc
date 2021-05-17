package com.unisul.leitor.pedido.itempedido.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_item")
public class ItemPedidoEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "order_id")
    private Long orderId;
    @ColumnInfo(name = "bar_code")
    private String codigoBarras;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(Long orderId, String codigoBarras) {
        this.orderId = orderId;
        this.codigoBarras = codigoBarras;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }
}
