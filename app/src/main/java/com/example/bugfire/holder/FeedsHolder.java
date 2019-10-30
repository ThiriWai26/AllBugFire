package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class FeedsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnFeedClickListener listener;
    private TextView txName, txTime, txabout, txabout1;
    private ImageView profile;

    public FeedsHolder(@NonNull View itemView, OnFeedClickListener listener) {
        super(itemView);
        this.listener = this.listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        txabout1 = itemView.findViewById(R.id.tvabout1);

        itemView.setOnClickListener(this);
    }

    public static FeedsHolder create(LayoutInflater inflater, ViewGroup parent, FeedsHolder.OnFeedClickListener listener) {
        View view = inflater.inflate(R.layout.layout_feed_item, parent, false);
        return new FeedsHolder(view, listener);
    }

    public static void BindData() {
    }

    @Override
    public void onClick(View v) {
    }

    public interface OnFeedClickListener {
    }

}
