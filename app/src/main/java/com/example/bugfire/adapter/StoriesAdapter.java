package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.StoriesHolder;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesHolder> {

    private StoriesHolder.OnStoriesItemClickListener listener;

    public StoriesAdapter(StoriesHolder.OnStoriesItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return StoriesHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull StoriesHolder holder, int position) {
        StoriesHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
