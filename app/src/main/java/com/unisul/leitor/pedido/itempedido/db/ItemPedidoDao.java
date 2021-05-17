package com.unisul.leitor.pedido.itempedido.db;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Insert;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface ItemPedidoDao {
    @Insert
    Completable insertItemPedido(@NonNull final List<ItemPedidoEntity> itemPedidoEntity);
}
