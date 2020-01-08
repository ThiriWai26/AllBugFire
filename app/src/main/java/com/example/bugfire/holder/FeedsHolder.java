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
import com.example.bugfire.rabbitconverter.rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

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

        if (userFont.equals("z")) {
            Log.e("font", "z");
            txName.setText(rabbit.uni2zg(feeds.name));
            txTime.setText(rabbit.uni2zg(feeds.date));
            txabout.setText(rabbit.uni2zg(feeds.content));
        } else {
            Log.e("font","u");
            txName.setText(rabbit.zg2uni(feeds.name));
            txTime.setText(rabbit.zg2uni(feeds.date));
            txabout.setText(rabbit.zg2uni(feeds.content));
        }

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feeds.categoryPhoto).into(profile);

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feeds.sourceLogo).into(logo);
    }

    public interface OnFeedClickListener {
        void onPCFeeds(int i);
    }

}

