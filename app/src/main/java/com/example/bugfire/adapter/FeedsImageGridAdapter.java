package com.example.bugfire.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.fragment.FeedsFragment;
import com.example.bugfire.holder.FeedsImageGridHolder;

import java.util.ArrayList;
import java.util.List;

public class FeedsImageGridAdapter extends RecyclerView.Adapter<FeedsImageGridHolder> {

    List<String> photos;

    public FeedsImageGridAdapter(FeedsFragment feedsImageGridFragment) {
        photos = new ArrayList<>();
    }

    @NonNull
    @Override
    public FeedsImageGridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return FeedsImageGridHolder.create(inflater,viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedsImageGridHolder holder, int position) {
        holder.bindData(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public void addItem(List<String> photos){
        this.photos.clear();
        this.photos.addAll(photos);
        notifyDataSetChanged();

    }
}
