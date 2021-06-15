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

    @Query("select * from `order` where status = 'Preenchido' and sincronizado = 0")
    Single<List<PedidoEntity>> getAllPedidosPreenchidosAndNotSync();

    @Insert
    Completable insertPedidos(@NonNull final List<PedidoEntity> pedidosEntity);

    @Query("select * from `order` where sincronizado = :sincronizado and status = :status")
    Single<List<PedidoEntity>> getAllPedidosBySincronizado(boolean sincronizado, String status);

    @Query("update 'order' set status = 'Preenchido' where id = :idPedido")
    Completable setPedidoLido(final long idPedido);

    @Query("update 'order' set sincronizado = 1 where id = :idPedido")
    Completable setPedidoSincronizado(final long idPedido);

    @Query("delete from 'order' where status = 'Pendente'")
    Completable deletePendentes();
}
