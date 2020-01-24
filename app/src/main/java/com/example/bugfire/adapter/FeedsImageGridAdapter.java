package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.holder.FeedsImageGridHolder;

import java.util.ArrayList;
import java.util.List;

public class FeedsImageGridAdapter extends RecyclerView.Adapter<FeedsImageGridHolder> {

    List<String> photo = new ArrayList<>();
    FeedsImageGridHolder.OnItemClickListener listener;

    public FeedsImageGridAdapter(FeedsImageGridHolder.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeedsImageGridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return FeedsImageGridHolder.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsImageGridHolder holder, int position) {
        holder.bindData(photo.get(position));
    }

    @Override
    public int getItemCount() {
        return photo.size();
    }

    public void addItem(List<String> photos){
        this.photo.clear();
        this.photo.addAll(photos);
        notifyDataSetChanged();

    }
}
