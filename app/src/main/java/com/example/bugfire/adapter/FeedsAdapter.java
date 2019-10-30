package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.FeedsHolder;


public class FeedsAdapter extends RecyclerView.Adapter<FeedsHolder> {

    FeedsHolder.OnFeedClickListener listener;

    public FeedsAdapter(FeedsHolder.OnFeedClickListener listener) {

        this.listener = listener;
    }

    @NonNull
    @Override
    public FeedsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return FeedsHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsHolder holder, int position) {
        FeedsHolder.BindData();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
