package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.EsportsHolder;
import com.example.bugfire.model.Article;

import java.util.ArrayList;
import java.util.List;

public class EsportsAdapter extends RecyclerView.Adapter<EsportsHolder> {

    List<Article> articleList;
    private EsportsHolder.OnEsportItemClickListener listener;

    public EsportsAdapter(EsportsHolder.OnEsportItemClickListener listener) {
        articleList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public EsportsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return EsportsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EsportsHolder esportsHolder, int position) {
        esportsHolder.bindData(articleList.get(position));
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

