package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.MobileHolder;

public class MobileAdapter extends RecyclerView.Adapter<MobileHolder> {

    private MobileHolder.OnMobileItemClickListener listener;

    public MobileAdapter(MobileHolder.OnMobileItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MobileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return MobileHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileHolder holder, int position) {
        MobileHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
