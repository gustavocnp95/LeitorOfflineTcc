package com.unisul.leitor.pedido.data;

import com.unisul.leitor.pedido.model.PedidoLeitura;
import com.unisul.leitor.pedido.model.PedidoListagem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created on 2021-06-13
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public interface PedidoRest {
    @GET("pedidos")
    Single<List<PedidoListagem>> getPedidosByStatus(@Query("status") final String status);

    @POST("pedidos")
    Completable sendItensPedido(@Body final PedidoLeitura pedidoLeitura);
}
