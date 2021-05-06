package com.unisul.leitor.statuspedidos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.unisul.leitor.R;
import com.unisul.leitor.databinding.FragmentStatusPedidosBinding;

public class StatusPedidosFragment extends Fragment {
    @Nullable
    private FragmentStatusPedidosBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_status_pedidos,
                container,
                false);
        setupSwipeToRefresh();
        return mBinding.getRoot();
    }

    private void setupSwipeToRefresh() {
        mBinding.layoutSwipeToRefresh.setOnRefreshListener(() -> {
            mBinding.layoutSwipeToRefresh.setRefreshing(false);
        });
        mBinding.layoutSwipeToRefresh.setColorSchemeResources(
                R.color.black,
                R.color.white);
    }
}
