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
import com.example.bugfire.rabbitconverter.rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class GamesHolder extends RecyclerView.ViewHolder {

    private OnGamesItemClickListener listener;
    private TextView txName, txTime, txabout;
    private ImageView profile, logo;

    public GamesHolder(@NonNull View itemView, GamesHolder.OnGamesItemClickListener listener) {

        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.profile);
        logo = itemView.findViewById(R.id.logo);
    }

    public static GamesHolder create(LayoutInflater inflater, ViewGroup parent, GamesHolder.OnGamesItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_feed_item, parent, false);
        return new GamesHolder(view, listener);
    }

    public void bindData(FeedsTopicList feedsTopicList) {

        if (userFont.equals("z")) {
            txName.setText(rabbit.uni2zg(feedsTopicList.name));
            txTime.setText(rabbit.uni2zg(feedsTopicList.date));
            txabout.setText(rabbit.uni2zg(feedsTopicList.content));
        } else {
            txName.setText(rabbit.zg2uni(feedsTopicList.name));
            txTime.setText(rabbit.zg2uni(feedsTopicList.date));
            txabout.setText(rabbit.zg2uni(feedsTopicList.content));
        }

        Picasso.get()
                .load(RetrofitService.BASE_URL + "/api/download_image/" + feedsTopicList.categoryPhoto)
                .resize(800, 700)
                .centerCrop()
                .into(profile);

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feedsTopicList.sourceLogo).into(logo);
    }

    public interface OnGamesItemClickListener {
    }
}
