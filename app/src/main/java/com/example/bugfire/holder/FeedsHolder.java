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
import com.example.bugfire.model.Feeds;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class FeedsHolder extends RecyclerView.ViewHolder {

    private OnFeedClickListener listener;
    private TextView txName, txTime, txabout;
    private ImageView profile,logo;

    public FeedsHolder(@NonNull View itemView, OnFeedClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.profile);
        logo = itemView.findViewById(R.id.logo);

    }

    public static FeedsHolder create(LayoutInflater inflater, ViewGroup parent, FeedsHolder.OnFeedClickListener listener) {
        View view = inflater.inflate(R.layout.layout_feed_item, parent, false);
        return new FeedsHolder(view, listener);
    }

    public void BindData(Feeds feeds) {

        txName.setText(feeds.name);
        txTime.setText(feeds.date);
        txabout.setText(feeds.content);

        Picasso.get()
                .load(RetrofitService.BASE_URL + feeds.categoryPhoto)
                .resize(800,700)
                .centerCrop()
                .into(profile);

        Picasso.get().load(RetrofitService.BASE_URL + feeds.sourceLogo).into(logo);

        Log.e("category_name", feeds.name);
        Log.e("date", feeds.date);
        Log.e("content", feeds.content);
        Log.e("source_logo", feeds.sourceLogo);
        Log.e("category_photo", feeds.categoryPhoto);

    }


    public interface OnFeedClickListener {
        void onPCFeeds(int i);
    }

}

