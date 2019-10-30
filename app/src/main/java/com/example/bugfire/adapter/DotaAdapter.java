package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.DotaHolder;

public class DotaAdapter extends RecyclerView.Adapter<DotaHolder> {

    private DotaHolder.OnDotaItemClickListener listener;

    public DotaAdapter(DotaHolder.OnDotaItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DotaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return DotaHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DotaHolder holder, int position) {
        DotaHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
