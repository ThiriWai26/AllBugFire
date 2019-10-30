package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class StoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnStoriesItemClickListener listener;

    public StoriesHolder(@NonNull View itemView, OnStoriesItemClickListener listener) {
        super(itemView);
        this.listener = listener;
    }

    public static StoriesHolder create(LayoutInflater inflater, ViewGroup parent, OnStoriesItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_stories_item, parent, false);
        return new StoriesHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onStoriesClick();
    }

    public interface OnStoriesItemClickListener {
        void onStoriesClick();
    }
}
