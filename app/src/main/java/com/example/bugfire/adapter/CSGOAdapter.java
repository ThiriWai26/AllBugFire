package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.CSGOHolder;

public class CSGOAdapter extends RecyclerView.Adapter<CSGOHolder> {

    private CSGOHolder.OnCSGOItemClickListener listener;

    public CSGOAdapter(CSGOHolder.OnCSGOItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CSGOHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return CSGOHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CSGOHolder holder, int position) {
        CSGOHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
