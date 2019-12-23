package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.TeamsDetailFeedsFragment;
import com.example.bugfire.holder.TeamsFeedHolder;
import com.example.bugfire.model.FeedsTopicList;

import java.util.ArrayList;
import java.util.List;

public class TeamsFeedAdapter extends RecyclerView.Adapter<TeamsFeedHolder> {

    private TeamsFeedHolder.OnTeamsFeedClickListener listener;
    List<FeedsTopicList> feedsTopicLists;

    public TeamsFeedAdapter(TeamsDetailFeedsFragment teamsDetailFeedsFragment) {
        this.listener = listener;
        feedsTopicLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public TeamsFeedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return TeamsFeedHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsFeedHolder holder, int position) {
        holder.bindData(feedsTopicLists.get(position));
    }


    @Override
    public int getItemCount() {
        return feedsTopicLists.size();
    }
    public
    void addItem(List<FeedsTopicList> feedsTopicList) {
        this.feedsTopicLists.clear();
        this.feedsTopicLists.addAll(feedsTopicList);
        notifyDataSetChanged();

    }

}
