package com.unisul.leitor.pedido;

import android.accounts.NetworkErrorException;
import android.content.Context;

import androidx.annotation.NonNull;

import com.unisul.leitor.common.AndroidUtils;

import io.reactivex.rxjava3.core.Completable;

public class PedidoRepositorio {
    public Completable enviarPedido(@NonNull final Context context,
                                    final long idPedido) {
        if (!AndroidUtils.isNetworkAvailable(context)) {
            return Completable.error(new NetworkErrorException("Sem conexão a internet!"));
        }
        return Completable.complete();
    }
}
