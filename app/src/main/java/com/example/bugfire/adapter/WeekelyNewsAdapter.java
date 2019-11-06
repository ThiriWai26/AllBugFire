package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.WeekelyNewsHolder;

public class WeekelyNewsAdapter extends RecyclerView.Adapter<WeekelyNewsHolder> {

    WeekelyNewsHolder.OnWeekelyNewsItemClickListener listener;

    public WeekelyNewsAdapter(WeekelyNewsHolder.OnWeekelyNewsItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public WeekelyNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return WeekelyNewsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekelyNewsHolder holder, int position) {
        WeekelyNewsHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
