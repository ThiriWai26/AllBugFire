package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.EsportsHolder;

public class EsportsAdapter extends RecyclerView.Adapter<EsportsHolder> {

    private EsportsHolder.OnEsportItemClickListener listener;

    public EsportsAdapter(EsportsHolder.OnEsportItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EsportsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return EsportsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EsportsHolder holder, int position) {
        EsportsHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
