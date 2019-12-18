package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PCGamesHolder;
import com.example.bugfire.model.GamesList;

import java.util.ArrayList;
import java.util.List;

public class PCGamesAdapter extends RecyclerView.Adapter<PCGamesHolder> {

    List<GamesList> gamesLists;
    private PCGamesHolder.OnPCItemClickListener listener;

    public PCGamesAdapter(PCGamesHolder.OnPCItemClickListener listener) {
        gamesLists = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public PCGamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PCGamesHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PCGamesHolder pcGamesHolder, int position) {
        pcGamesHolder.bindData(gamesLists.get(position));
    }

    @Override
    public int getItemCount() {
        return gamesLists.size();
    }

    public void addItem(List<GamesList> gamesList) {
        this.gamesLists.clear();
        this.gamesLists.addAll(gamesList);
        notifyDataSetChanged();

    }
}
