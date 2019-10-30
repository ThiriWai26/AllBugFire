package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.NewsHolder;

public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    NewsHolder.OnNewsClickListener listener;

    public NewsAdapter(NewsHolder.OnNewsClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return NewsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
