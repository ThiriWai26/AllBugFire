package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PlayersFeedHolder;

public class PlayersFeedAdapter extends RecyclerView.Adapter<PlayersFeedHolder> {

    private PlayersFeedHolder.OnPlayersFeedClickListener listener;
    private TextView txName, txTime, txabout, txabout1;
    private ImageView profile;

    public PlayersFeedAdapter(PlayersFeedHolder.OnPlayersFeedClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayersFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayersFeedHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersFeedHolder holder, int position) {
        PlayersFeedHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
