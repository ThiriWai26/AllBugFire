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

public class TeamsNewHolder extends RecyclerView.ViewHolder {

    private TeamsNewHolder.OnTeamsNewClickListener listener;
    private TextView txName, txabout, tvId;
    private ImageView profile;
    private RelativeLayout layout;

    public TeamsNewHolder(@NonNull View itemView, TeamsNewHolder.OnTeamsNewClickListener listener) {
        super(itemView);
        this.listener = listener;

        txName = itemView.findViewById(R.id.tvtitle);
        txabout = itemView.findViewById(R.id.tvabout);
        profile = itemView.findViewById(R.id.featurephoto);
        tvId = itemView.findViewById(R.id.tvId);
        layout = itemView.findViewById(R.id.layout);
    }

    public static TeamsNewHolder create(LayoutInflater inflater, ViewGroup parent, TeamsNewHolder.OnTeamsNewClickListener listener) {
        View view = inflater.inflate(R.layout.layout_teamsnews_item, parent, false);
        return new TeamsNewHolder(view, listener);
    }

    public void bindData(final NewsTopicList newsTopicList) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + newsTopicList.featurephoto).into(profile);
        txName.setText(newsTopicList.title);
        txabout.setText(newsTopicList.preview);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTeamsNewClick(newsTopicList.id);
            }
        });
    }

    public interface OnTeamsNewClickListener {
        void onTeamsNewClick(int id);
    }
}
