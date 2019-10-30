package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PCGamesHolder;

public class PCGamesAdapter extends RecyclerView.Adapter<PCGamesHolder> {

    private PCGamesHolder.OnPCItemClickListener listener;

    public PCGamesAdapter(PCGamesHolder.OnPCItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PCGamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PCGamesHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PCGamesHolder holder, int position) {
        PCGamesHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
