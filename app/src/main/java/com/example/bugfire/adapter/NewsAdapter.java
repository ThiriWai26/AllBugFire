package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.NewsHolder;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    List<News> newsList;
    NewsHolder.OnNewsClickListener listener;

    public NewsAdapter(NewsHolder.OnNewsClickListener listener) {
        newsList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return NewsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int position) {
        newsHolder.bindData(newsList.get(position));
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void addItem(List<News> news){

        this.newsList.clear();
        this.newsList.addAll(news);
        notifyDataSetChanged();

    }
}
