package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.TopicFeeds;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class PlayersFeedHolder extends RecyclerView.ViewHolder  {
    
    private OnPlayersFeedClickListener listener;
    private TextView txName, txTime, txabout;
    private ImageView profile, logo;
    
    public PlayersFeedHolder(@NonNull View itemView, OnPlayersFeedClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.profile);
        logo = itemView.findViewById(R.id.logo);

    }

    public static PlayersFeedHolder create(LayoutInflater inflater, ViewGroup parent, OnPlayersFeedClickListener listener) {
        View view = inflater.inflate(R.layout.layout_playerfeeds_item, parent, false);
        return new PlayersFeedHolder(view, listener);
    }

    public void bindData(FeedsTopicList feedsTopicList) {
        txName.setText(feedsTopicList.name);
        txTime.setText(feedsTopicList.date);
        txabout.setText(feedsTopicList.content);

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feedsTopicList.sourceLogo).into(logo);

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feedsTopicList.categoryPhoto).into(profile);

//        Log.e("category_name", feedsTopicList.name);
//        Log.e("date", feedsTopicList.date);
//        Log.e("content", feedsTopicList.content);
//        Log.e("source_logo", feedsTopicList.sourceLogo);
//        Log.e("category_photo", feedsTopicList.categoryPhoto);
    }

    public interface OnPlayersFeedClickListener {
        void onPlayersFeedClick();
    }
}
