package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.TopicFeeds;

public class PlayersFeedHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    
    private OnPlayersFeedClickListener listener;
    private TextView txName, txTime, txabout, txabout1;
    private ImageView profile;
    
    public PlayersFeedHolder(@NonNull View itemView, OnPlayersFeedClickListener listener) {
        super(itemView);
        this.listener = this.listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        txabout1 = itemView.findViewById(R.id.tvabout1);

        itemView.setOnClickListener(this);
    }

    public static PlayersFeedHolder create(LayoutInflater inflater, ViewGroup parent, OnPlayersFeedClickListener listener) {
        View view = inflater.inflate(R.layout.layout_playerfeeds_item, parent, false);
        return new PlayersFeedHolder(view, listener);
    }

    public static void bindData(TopicFeeds topicFeeds) {
    }

    @Override
    public void onClick(View v) {
    }

    public interface OnPlayersFeedClickListener {
        void onPlayersFeedClick();
    }
}
