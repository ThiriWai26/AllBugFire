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
import com.example.bugfire.rabbitconverter.rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class PlayersNewHolder extends RecyclerView.ViewHolder {

    private OnPlayersNewClickListener listener;
    private TextView txName, txabout, tvId;
    private ImageView profile;
    private RelativeLayout layout;

    public PlayersNewHolder(@NonNull View itemView, PlayersNewHolder.OnPlayersNewClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvtitle);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.featurephoto);
        tvId = itemView.findViewById(R.id.tvId);
        layout = itemView.findViewById(R.id.layout);
    }

    public static PlayersNewHolder create(LayoutInflater inflater, ViewGroup parent, PlayersNewHolder.OnPlayersNewClickListener listener) {
        View view = inflater.inflate(R.layout.layout_playernews_item, parent, false);
        return new PlayersNewHolder(view, listener);
    }

    public void bindData(final NewsTopicList feedsTopicList) {
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + feedsTopicList.featurephoto).into(profile);

        if (userFont.equals("z")) {
            txName.setText(rabbit.uni2zg(feedsTopicList.title));
            txabout.setText(rabbit.uni2zg(feedsTopicList.preview));
        } else {
            txName.setText(rabbit.zg2uni(feedsTopicList.title));
            txabout.setText(rabbit.zg2uni(feedsTopicList.preview));
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlayersNewClick(feedsTopicList.id);
            }
        });
    }

    public interface OnPlayersNewClickListener {
        void onPlayersNewClick(int id);
    }
}
