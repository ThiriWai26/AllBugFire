package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PlayersFeedHolder;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.TopicFeeds;

import java.util.ArrayList;
import java.util.List;

public class PlayersFeedAdapter extends RecyclerView.Adapter<PlayersFeedHolder> {

    private PlayersFeedHolder.OnPlayersFeedClickListener listener;
    List<FeedsTopicList> feedsTopicLists;

    public PlayersFeedAdapter(PlayersFeedHolder.OnPlayersFeedClickListener listener) {
        this.listener = listener;
        feedsTopicLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlayersFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayersFeedHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersFeedHolder playersFeedHolder, int position) {
        playersFeedHolder.bindData(feedsTopicLists.get(position));
    }

    @Override
    public int getItemCount() {
        return feedsTopicLists.size();
    }

    public void addItem(List<FeedsTopicList> feedsTopicList) {
            this.feedsTopicLists.clear();
            this.feedsTopicLists.addAll(feedsTopicList);
            notifyDataSetChanged();

    }
}
