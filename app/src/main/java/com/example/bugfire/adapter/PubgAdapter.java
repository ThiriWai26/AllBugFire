package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.PubgFragment;
import com.example.bugfire.holder.PubgHolder;
import com.example.bugfire.model.Article;

import java.util.ArrayList;
import java.util.List;

public class PubgAdapter extends RecyclerView.Adapter<PubgHolder> {

    List<Article> articleList;
    private PubgHolder.OnPubgItemClickListener listener;

    public PubgAdapter(PubgHolder.OnPubgItemClickListener listener) {
        articleList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public PubgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PubgHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PubgHolder pubgHolder, int position) {
        pubgHolder.bindData(articleList.get(position));
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
