package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.CSGOHolder;
import com.example.bugfire.model.Article;

import java.util.ArrayList;
import java.util.List;

public class CSGOAdapter extends RecyclerView.Adapter<CSGOHolder> {

    List<Article> articleList;
    private CSGOHolder.OnCSGOItemClickListener listener;

    public CSGOAdapter(CSGOHolder.OnCSGOItemClickListener listener) {
        articleList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public CSGOHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return CSGOHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CSGOHolder csgoHolder, int position) {
        csgoHolder.bindData(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void addItem(List<Article> articles) {

        this.articleList.clear();
        this.articleList.addAll(articles);
        notifyDataSetChanged();
    }
}
