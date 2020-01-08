package com.example.bugfire.holder;

import android.content.Intent;
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
import com.example.bugfire.activity.GamesNewsDetailActivity;
import com.example.bugfire.model.NewsTopicList;
import com.example.bugfire.rabbitconverter.rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class GamesNewsHolder extends RecyclerView.ViewHolder {

    private OnGamesNewsItemClickListener listener;
    private TextView txName, txabout, tvId;
    private ImageView featurephoto;
    private RelativeLayout layout;

    public GamesNewsHolder(@NonNull View itemView, GamesNewsHolder.OnGamesNewsItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        tvId = itemView.findViewById(R.id.tvId);
        txName = itemView.findViewById(R.id.tvtitle);
        txabout = itemView.findViewById(R.id.tvabout);
        featurephoto = itemView.findViewById(R.id.featurephoto);
        layout = itemView.findViewById(R.id.layout);
    }

    public static GamesNewsHolder create(LayoutInflater inflater, ViewGroup parent, GamesNewsHolder.OnGamesNewsItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_gamesnews_item, parent, false);
        return new GamesNewsHolder(view, listener);
    }

    public void bindData(final NewsTopicList newsTopicList) {

        if (userFont.equals("z")) {
            txName.setText(rabbit.uni2zg(newsTopicList.title));
            txabout.setText(rabbit.uni2zg(newsTopicList.preview));
        } else {
            txName.setText(rabbit.zg2uni(newsTopicList.title));
            txabout.setText(rabbit.zg2uni(newsTopicList.preview));
        }
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + newsTopicList.featurephoto).into(featurephoto);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGamesNewsClick(newsTopicList.id);
            }
        });
    }

    public interface OnGamesNewsItemClickListener {
        void onGamesNewsClick(int id);
    }
}
