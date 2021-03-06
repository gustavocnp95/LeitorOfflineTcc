package com.unisul.leitor.pedido.db;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface PedidoDao {
    @Query("select * from `order`")
    Single<List<PedidoEntity>> getAllPedidos();

    @Insert
    void insertPedido(@NonNull final PedidoEntity pedidoEntity);

    @Query("select * from `order` where sincronizado = :sincronizado and status = :status")
    Single<List<PedidoEntity>> getAllPedidosBySincronizado(boolean sincronizado, String status);

    @Query("update 'order' set status = 'Preenchido' where id = :idPedido")
    Completable setPedidoLido(final long idPedido);
}
