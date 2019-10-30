package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.PubgFragment;
import com.example.bugfire.holder.PubgHolder;

public class PubgAdapter extends RecyclerView.Adapter<PubgHolder> {

    private PubgHolder.OnPubgItemClickListener listener;

    public PubgAdapter(PubgHolder.OnPubgItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PubgHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return PubgHolder.create(inflater,parent,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PubgHolder holder, int position) {
        PubgHolder.bindData();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
