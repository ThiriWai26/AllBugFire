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
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.NewsTopicList;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class PlayersNewHolder extends RecyclerView.ViewHolder {

    private OnPlayersNewClickListener listener;
    private TextView txName, txTime, txabout;
    private ImageView profile, logo;
    private RelativeLayout layout;

    public PlayersNewHolder(@NonNull View itemView, PlayersNewHolder.OnPlayersNewClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvName);
        txTime = itemView.findViewById(R.id.tvTime);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.profile);
        logo = itemView.findViewById(R.id.logo);
        layout = itemView.findViewById(R.id.layout);
    }

    public static PlayersNewHolder create(LayoutInflater inflater, ViewGroup parent, PlayersNewHolder.OnPlayersNewClickListener listener) {
        View view = inflater.inflate(R.layout.layout_playernews_item, parent, false);
        return new PlayersNewHolder(view, listener);
    }

    public void bindData(final NewsTopicList feedsTopicList) {
        txName.setText(feedsTopicList.title);
        txabout.setText(feedsTopicList.preview);

        Picasso.get()
                .load(RetrofitService.BASE_URL + feedsTopicList.categoryphoto)
                .resize(800,700)
                .centerCrop()
                .into(profile);


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlayersNewClick(feedsTopicList.id);
            }
        });

        Log.e("id", String.valueOf(feedsTopicList.id));
        Log.e("title", feedsTopicList.title);
        Log.e("Preview", feedsTopicList.preview);
        Log.e("featurePhoto", feedsTopicList.featurephoto);
    }

    public interface OnPlayersNewClickListener {
        void onPlayersNewClick(int id);
    }
}
