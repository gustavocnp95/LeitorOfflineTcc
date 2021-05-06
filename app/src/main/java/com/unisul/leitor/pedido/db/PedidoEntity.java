package com.unisul.leitor.pedido.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pedido")
public class PedidoEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "codigo_pedido")
    private long codPedido;

    public PedidoEntity() {
    }

    public PedidoEntity(Long id, long codPedido) {
        this.id = id;
        this.codPedido = codPedido;
    }

    public PedidoEntity(long codPedido) {
        this.codPedido = codPedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(long codPedido) {
        this.codPedido = codPedido;
    }
}
