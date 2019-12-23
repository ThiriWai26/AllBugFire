package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.PlayersNewsFragment;
import com.example.bugfire.holder.PlayersNewHolder;
import com.example.bugfire.model.NewsTopicList;

import java.util.ArrayList;
import java.util.List;

public class PlayersNewAdapter extends RecyclerView.Adapter<PlayersNewHolder> {

    private PlayersNewHolder.OnPlayersNewClickListener listener;
    List<NewsTopicList> newsTopicLists;

    public PlayersNewAdapter(PlayersNewsFragment playersNewsFragment) {
        this.listener = listener;
        newsTopicLists = new ArrayList<>();
    }

    @NonNull
    @Override
    public PlayersNewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PlayersNewHolder.create(inflater,parent,listener);    }

    @Override
    public void onBindViewHolder(@NonNull PlayersNewHolder holder, int position) {
        holder.bindData(newsTopicLists.get(position));
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
