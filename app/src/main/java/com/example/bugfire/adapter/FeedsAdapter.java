package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.model.Feeds;

import java.util.ArrayList;
import java.util.List;


public class FeedsAdapter extends RecyclerView.Adapter<FeedsHolder> {

    List<Feeds> feedsList ;
    FeedsHolder.OnFeedClickListener listener;

    public FeedsAdapter(FeedsHolder.OnFeedClickListener listener) {

        feedsList = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return FeedsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsHolder feedsHolder, int position) {
        feedsHolder.BindData(feedsList.get(position));
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    public void addItem(List<Feeds> feeds){

        this.feedsList.clear();
        this.feedsList.addAll(feeds);
        notifyDataSetChanged();

    }
}
