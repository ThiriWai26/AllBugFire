package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.PCGamesDetailNewsFragment;
import com.example.bugfire.holder.GamesNewsHolder;
import com.example.bugfire.model.NewsTopicList;

import java.util.ArrayList;
import java.util.List;

public class GamesNewsAdapter extends RecyclerView.Adapter<GamesNewsHolder> {

    List<NewsTopicList> newsTopicList;
    GamesNewsHolder.OnGamesNewsItemClickListener listener;

    public GamesNewsAdapter(GamesNewsHolder.OnGamesNewsItemClickListener listener ) {
        this.listener = listener;
        newsTopicList = new ArrayList<>();
    }

    @NonNull
    @Override
    public GamesNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return GamesNewsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesNewsHolder gamesNewsHolder, int position) {
        gamesNewsHolder.bindData(newsTopicList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsTopicList.size();
    }

    public void addItem(List<NewsTopicList> newsTopicLists){

        this.newsTopicList.clear();
        this.newsTopicList.addAll(newsTopicLists);
        notifyDataSetChanged();

    }
}
