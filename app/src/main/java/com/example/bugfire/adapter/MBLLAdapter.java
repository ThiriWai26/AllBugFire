package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bugfire.holder.MBLLHolder;

public class MBLLAdapter extends RecyclerView.Adapter<MBLLHolder> {

    private MBLLHolder.OnMBLLItemClickListener listener;

    public MBLLAdapter(MBLLHolder.OnMBLLItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MBLLHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return MBLLHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MBLLHolder holder, int position) {
        MBLLHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
