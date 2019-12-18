package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PlayerHolder;
import com.example.bugfire.model.PlayerList;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

    List<PlayerList> playerLists;
    private PlayerHolder.OnPlayerItemClickListener listener;

    public PlayerAdapter(PlayerHolder.OnPlayerItemClickListener listener) {
        playerLists = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayerHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder playerHolder, int position) {
        playerHolder.bindData(playerLists.get(position));
    }

    @Override
    public int getItemCount() {
        return playerLists.size();
    }

    public void addItem(List<PlayerList> playerList){

        this.playerLists.clear();
//        this.playerLists.addAll(playerList);
        notifyDataSetChanged();

    }
}
