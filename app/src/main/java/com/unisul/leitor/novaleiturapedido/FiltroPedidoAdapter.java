package com.unisul.leitor.novaleiturapedido;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unisul.leitor.novaleiturapedido.model.PedidoFiltro;

import java.util.List;

public class FiltroPedidoAdapter extends ArrayAdapter<PedidoFiltro> {
    @NonNull
    private final List<PedidoFiltro> pedidos;

    public FiltroPedidoAdapter(@NonNull final Context context,
                               final int textViewResourceId,
                               @NonNull final List<PedidoFiltro> pedidos) {
        super(context, textViewResourceId, pedidos);
        this.pedidos = pedidos;
    }

    @Override
    public int getCount() {
        return pedidos.size();
    }

    @NonNull
    @Override
    public PedidoFiltro getItem(int position) {
        return pedidos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position,
                        @NonNull final View convertView,
                        @NonNull final ViewGroup parent) {
        return defaultTextView(
                position, (TextView) super.getView(position, convertView, parent));
    }

    @NonNull
    @Override
    public View getDropDownView(final int position,
                                @Nullable final View convertView,
                                @NonNull final ViewGroup parent) {
        return defaultTextView(
                position, (TextView) super.getDropDownView(position, convertView, parent));
    }

    @NonNull
    private TextView defaultTextView(final int position, @NonNull final TextView label) {
        label.setTextColor(Color.BLACK);
        label.setText(String.valueOf(pedidos.get(position).getCodPedido()));
        label.setGravity(Gravity.CENTER);
        return label;
    }
}
