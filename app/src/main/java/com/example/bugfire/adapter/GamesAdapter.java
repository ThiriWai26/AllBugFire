package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.GamesHolder;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.model.FeedsTopicList;

import java.util.ArrayList;
import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesHolder> {

    List<FeedsTopicList> feedsTopicList;
    GamesHolder.OnGamesItemClickListener listener;

    public GamesAdapter(GamesHolder.OnGamesItemClickListener listener) {

        feedsTopicList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public GamesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return GamesHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesHolder gamesHolder, int position) {
        gamesHolder.bindData(feedsTopicList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedsTopicList.size();
    }

    public void addItem(List<FeedsTopicList> feedsTopicLists){

        this.feedsTopicList.clear();
        this.feedsTopicList.addAll(feedsTopicLists);
        notifyDataSetChanged();

    }
}
