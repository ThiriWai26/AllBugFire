package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.TeamsDetailNewsFragment;
import com.example.bugfire.holder.PlayersNewHolder;
import com.example.bugfire.holder.TeamsNewHolder;
import com.example.bugfire.model.NewsTopicList;

import java.util.ArrayList;
import java.util.List;

public class TeamsNewAdapter extends RecyclerView.Adapter<TeamsNewHolder> {

    private TeamsNewHolder.OnTeamsNewClickListener listener;
    List<NewsTopicList> newsTopicLists;

    public TeamsNewAdapter(TeamsNewHolder.OnTeamsNewClickListener listener) {
        this.listener = listener;
        newsTopicLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public TeamsNewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return TeamsNewHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsNewHolder teamsNewHolder, int position) {
        teamsNewHolder.bindData(newsTopicLists.get(position));
    }

    @Override
    public int getItemCount() {
        return newsTopicLists.size();
    }

    public void addItem(List<NewsTopicList> newsTopicList) {
        this.newsTopicLists.clear();
        this.newsTopicLists.addAll(newsTopicList);
        notifyDataSetChanged();
    }

}
