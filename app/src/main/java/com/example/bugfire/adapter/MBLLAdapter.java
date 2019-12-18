package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.MBLLHolder;
import com.example.bugfire.model.Article;

import java.util.ArrayList;
import java.util.List;

public class MBLLAdapter extends RecyclerView.Adapter<MBLLHolder> {

    List<Article> articleList;
    private MBLLHolder.OnMBLLItemClickListener listener;

    public MBLLAdapter(MBLLHolder.OnMBLLItemClickListener listener) {
        articleList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public MBLLHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return MBLLHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MBLLHolder mbllHolder, int position) {
        mbllHolder.bindData(articleList.get(position));
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
