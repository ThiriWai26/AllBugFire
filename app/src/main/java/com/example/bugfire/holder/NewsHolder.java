package com.example.bugfire.holder;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnNewsClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout;

    public NewsHolder(View view, OnNewsClickListener listener) {
        super(view);

        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);

        view.setOnClickListener(this);
    }

    public static NewsHolder create(LayoutInflater inflater, ViewGroup parent, OnNewsClickListener listener) {
        View view = inflater.inflate(R.layout.layout_news_item, parent, false);
        return new NewsHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onNewsClick();

    }

    public interface OnNewsClickListener {
        public void onNewsClick();
    }

}
