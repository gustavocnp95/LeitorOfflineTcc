package com.unisul.leitor.pedido;

import android.accounts.NetworkErrorException;
import android.content.Context;

import androidx.annotation.NonNull;

import com.unisul.leitor.BaseRepositorio;
import com.unisul.leitor.common.AndroidUtils;
import com.unisul.leitor.pedido.data.PedidoRest;
import com.unisul.leitor.pedido.db.PedidoEntity;
import com.unisul.leitor.pedido.model.PedidoLeitura;
import com.unisul.leitor.pedido.model.PedidoListagem;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class PedidoRepositorio extends BaseRepositorio {
    public Completable enviarPedido(@NonNull final Context context,
                                    @NonNull final PedidoLeitura pedidoLeitura) {
        if (!AndroidUtils.isNetworkAvailable(context)) {
            return Completable.error(new NetworkErrorException("Sem conexão a internet!"));
        }
        return getRestService(PedidoRest.class)
                .sendItensPedido(pedidoLeitura)
                .onErrorResumeNext(Completable::error);
    }

    public Single<List<PedidoListagem>> syncPedidosPendentes(@NonNull final Context context) {
        if (!AndroidUtils.isNetworkAvailable(context)) {
            return Single.just(Collections.emptyList());
        }
        return getRestService(PedidoRest.class)
                .getPedidosByStatus("Pendente")
                .onErrorResumeNext(Single::error);
    }
}
