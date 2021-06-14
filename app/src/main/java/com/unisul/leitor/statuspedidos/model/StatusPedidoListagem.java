package com.unisul.leitor.statuspedidos.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.unisul.leitor.BR;

public class StatusPedidoListagem extends BaseObservable {
    private final long idPedido;
    @NonNull
    private final String codPedido;
    @NonNull
    private final String quantidadeItens;
    @NonNull
    private final String statusPedido;
    private boolean sincronizado;

    public StatusPedidoListagem(final long idPedido,
                                @NonNull final String codPedido,
                                @NonNull final String quantidadeItens,
                                @NonNull final String statusPedido,
                                final boolean sincronizado) {
        this.idPedido = idPedido;
        this.codPedido = codPedido;
        this.quantidadeItens = quantidadeItens;
        this.statusPedido = statusPedido;
        this.sincronizado = sincronizado;
    }

    public long getIdPedido() {
        return idPedido;
    }

    @NonNull
    public String getCodPedido() {
        return codPedido;
    }

    @NonNull
    public String getQuantidadeItens() {
        return quantidadeItens;
    }

    @NonNull
    public String getStatusPedido() {
        return statusPedido;
    }

    @Bindable
    @NonNull
    public Boolean getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(@NonNull Boolean sincronizado) {
        this.sincronizado = sincronizado;
        notifyPropertyChanged(BR.sincronizado);
    }

    public boolean isPreenchido() {
        return this.statusPedido.equals("Preenchido");
    }
}