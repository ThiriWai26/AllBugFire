package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.NewsTopicList;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class GamesNewsHolder extends RecyclerView.ViewHolder {

    private OnGamesNewsItemClickListener listener;
    private TextView txName, txabout;
    private ImageView profile,logo, featurephoto;
    private RelativeLayout layout;

    public GamesNewsHolder(@NonNull View itemView, GamesNewsHolder.OnGamesNewsItemClickListener listener) {
        super(itemView);
        this.listener =  listener;

        txName = itemView.findViewById(R.id.tvtitle);
        txabout = itemView.findViewById(R.id.tvabout);
        featurephoto = itemView.findViewById(R.id.featurephoto);
        logo = itemView.findViewById(R.id.logo);
        layout = itemView.findViewById(R.id.layout);
    }

    public static GamesNewsHolder create(LayoutInflater inflater, ViewGroup parent, GamesNewsHolder.OnGamesNewsItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_news_item, parent, false);
        return new GamesNewsHolder(view, listener);
    }

    public void bindData(final NewsTopicList newsTopicList) {

        txName.setText(newsTopicList.name);
        txabout.setText(newsTopicList.content);

        Picasso.get()
                .load(RetrofitService.BASE_URL + newsTopicList.sourceLogo)
                .resize(800,700)
                .centerCrop()
                .into(featurephoto);

//        Picasso.get().load(RetrofitService.BASE_URL + newsTopicList.sourceLogo).into(logo);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGamesNewsClick(newsTopicList.id);
            }
        });

//        Log.e("category_name", newsTopicList.name);
//        Log.e("date", newsTopicList.date);
//        Log.e("content", newsTopicList.content);
//        Log.e("source_logo", newsTopicList.sourceLogo);
//        Log.e("category_photo", newsTopicList.categoryPhoto);
    }

    public interface OnGamesNewsItemClickListener {
        void onGamesNewsClick(int id);
    }
}
