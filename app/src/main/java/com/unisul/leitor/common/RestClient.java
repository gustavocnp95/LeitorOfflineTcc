package com.unisul.leitor.common;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;


/**
 * Created on 2021-06-13
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public final class RestClient {
    @NonNull
    private static final Retrofit RETROFIT;

    static {
        RETROFIT = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl("http://192.168.0.19:8080/tcc/")
                .client(new OkHttpClient())
                .build();
    }

    private RestClient() {

    }

    public static <T> T getService(final Class<T> serviceClass) {
        return RETROFIT.create(serviceClass);
    }
}