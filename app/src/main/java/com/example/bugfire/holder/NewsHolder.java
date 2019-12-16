package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.News;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnNewsClickListener listener;
    private ImageView featurephoto;
    private TextView tvid, tvtitle, tvabout;

    public NewsHolder(View view, OnNewsClickListener listener) {
        super(view);
        this.listener = listener;

        tvid = view.findViewById(R.id.tvId);
        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);

        view.setOnClickListener(this);
    }

    public static NewsHolder create(LayoutInflater inflater, ViewGroup parent, OnNewsClickListener listener) {
        View view = inflater.inflate(R.layout.layout_news_item, parent, false);
        return new NewsHolder(view, listener);
    }

    public void bindData(News news) {

        tvid.setText(news.id);
        Picasso.get().load(RetrofitService.BASE_URL + news.featurePhoto).into(featurephoto);
        tvtitle.setText(news.title);
        tvabout.setText(news.preview);

        Log.e("id", String.valueOf(news.id));
        Log.e("featurePhoto", news.featurePhoto);
        Log.e("title", news.title);
        Log.e("preview", news.preview);

    }

    @Override
    public void onClick(View v) {
        listener.onNewsClick(Integer.parseInt((String) tvid.getText()));
        int position;
        position = getAdapterPosition();
        Log.e("position", String.valueOf(position));

    }

    public interface OnNewsClickListener {
        public void onNewsClick(int id);
    }

}
