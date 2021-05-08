package com.unisul.leitor.pedido.db;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PedidoDao {
    @Query("select * from `order`")
    List<PedidoEntity> getAllPedidos();

    @Insert
    void insertPedido(@NonNull final PedidoEntity pedidoEntity);
}
