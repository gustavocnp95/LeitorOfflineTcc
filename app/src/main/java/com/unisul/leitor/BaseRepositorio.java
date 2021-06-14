package com.unisul.leitor;

import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;

import com.unisul.leitor.common.RestClient;

/**
 * Created on 2021-06-13
 *
 * @author Gustavo Navarro (https://github.com/gustavocnp95)
 */
public abstract class BaseRepositorio {
    @NonNull
    private static final SimpleArrayMap<String, Object> sRestServicesCache = new SimpleArrayMap<>();

    protected <T> T getRestService(@NonNull final Class<T> serviceClass) {
        final String canonicalName = serviceClass.getCanonicalName();
        if (sRestServicesCache.get(canonicalName) == null) {
            final T service = RestClient.getService(serviceClass);
            sRestServicesCache.put(canonicalName, service);
            return service;
        }
        //noinspection unchecked
        return (T) sRestServicesCache.get(canonicalName);
    }
}
