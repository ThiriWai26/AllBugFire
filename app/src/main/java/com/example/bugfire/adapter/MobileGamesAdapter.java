package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.MobileGamesHolder;
import com.example.bugfire.model.GamesList;

import java.util.ArrayList;
import java.util.List;

public class MobileGamesAdapter extends RecyclerView.Adapter<MobileGamesHolder> {

    List<GamesList> gamesLists;
    private MobileGamesHolder.OnMobileItemClickListener listener;

    public MobileGamesAdapter(MobileGamesHolder.OnMobileItemClickListener listener) {
        this.listener = listener;
        gamesLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public MobileGamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return MobileGamesHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileGamesHolder mobileGamesHolder, int position) {
        mobileGamesHolder.bindData(gamesLists.get(position));
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
