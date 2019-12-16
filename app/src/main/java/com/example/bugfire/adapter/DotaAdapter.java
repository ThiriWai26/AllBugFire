package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.DotaHolder;
import com.example.bugfire.model.Article;
import com.example.bugfire.model.News;

import java.util.ArrayList;
import java.util.List;

public class DotaAdapter extends RecyclerView.Adapter<DotaHolder> {

    List<Article> articleList;
    private DotaHolder.OnDotaItemClickListener listener;

    public DotaAdapter(DotaHolder.OnDotaItemClickListener listener) {
        articleList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public DotaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return DotaHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull DotaHolder dotaHolder, int position) {
        dotaHolder.bindData(articleList.get(position));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void addItem(List<Article> articles){

        this.articleList.clear();
        this.articleList.addAll(articles);
        notifyDataSetChanged();

    }

}
