package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.model.News;
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class NewsHolder extends RecyclerView.ViewHolder {

    private OnNewsClickListener listener;
    private ImageView featurephoto;
    private TextView tvid, tvtitle, tvabout;
    private RelativeLayout layout;

    public NewsHolder(View view, OnNewsClickListener listener) {
        super(view);
        this.listener = listener;

        tvid = view.findViewById(R.id.tvId);
        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        layout = view.findViewById(R.id.layout);
    }

    public static NewsHolder create(LayoutInflater inflater, ViewGroup parent, OnNewsClickListener listener) {
        View view = inflater.inflate(R.layout.layout_news_item, parent, false);
        return new NewsHolder(view, listener);
    }

    public void bindData(final News news) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + news.featurePhoto).into(featurephoto);
        if (userFont.equals('z')) {
            tvtitle.setText(Rabbit.uni2zg(news.title));
            tvabout.setText(Rabbit.uni2zg(news.preview));
        } else {
            tvtitle.setText(Rabbit.zg2uni(news.title));
            tvabout.setText(Rabbit.zg2uni(news.preview));
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNewsClick(news.id);
            }
        });

    }

    public interface OnNewsClickListener {
        public void onNewsClick(int id);
    }

}
