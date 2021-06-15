package com.unisul.leitor.pedido.itempedido.db;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ItemPedidoDao {
    @Insert
    Completable insertItemPedido(@NonNull final List<ItemPedidoEntity> itemPedidoEntity);

    @Query("select * from `order_item` where order_id = :codPedido")
    Single<List<ItemPedidoEntity>> getItensPedido(final long codPedido);
}
