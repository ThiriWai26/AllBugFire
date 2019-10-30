package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PlayerHolder;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

    private PlayerHolder.OnPlayerItemClickListener listener;

    public PlayerAdapter(PlayerHolder.OnPlayerItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayerHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        PlayerHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
