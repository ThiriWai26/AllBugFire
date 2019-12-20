package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.MobileHolder;
import com.example.bugfire.model.GamesList;

import java.util.ArrayList;
import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileHolder> {

    List<GamesList> gamesLists;
    private MobileHolder.OnMobileItemClickListener listener;

    public MobileAdapter(MobileHolder.OnMobileItemClickListener listener) {
        this.listener = listener;
        gamesLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public MobileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return MobileHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileHolder mobileHolder, int position) {
        mobileHolder.bindData(gamesLists.get(position));
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
