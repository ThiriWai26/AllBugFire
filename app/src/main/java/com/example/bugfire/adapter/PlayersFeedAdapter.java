package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.PlayersFeedHolder;
import com.example.bugfire.model.TopicFeeds;

import java.util.ArrayList;
import java.util.List;

public class PlayersFeedAdapter extends RecyclerView.Adapter<PlayersFeedHolder> {

    private PlayersFeedHolder.OnPlayersFeedClickListener listener;
    List<TopicFeeds> topicFeedsList;

    public PlayersFeedAdapter(PlayersFeedHolder.OnPlayersFeedClickListener listener) {
        this.listener = listener;
        topicFeedsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlayersFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayersFeedHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersFeedHolder holder, int position) {
        PlayersFeedHolder.bindData(topicFeedsList.get(position));
    }

    @Override
    public int getItemCount() {
        return topicFeedsList.size();
    }
}
