package com.unisul.leitor.pedido.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order")
public class PedidoEntity {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "order_id")
    private long codPedido;
    @ColumnInfo(name = "itens_type")
    private String tipoItens;
    @ColumnInfo(name = "quantity_itens")
    private long quantidadeItens;
    @ColumnInfo(name = "customer_name")
    private String nomeCliente;
    @ColumnInfo(name = "status")
    private String status;

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

    public String getTipoItens() {
        return tipoItens;
    }

    public void setTipoItens(String tipoItens) {
        this.tipoItens = tipoItens;
    }

    public long getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(long quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
