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
import com.example.bugfire.model.Article;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

public class EsportsHolder extends RecyclerView.ViewHolder  {

    private OnEsportItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout, tvId;
    private RelativeLayout layout;

    public EsportsHolder(@NonNull View view, OnEsportItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        tvId = view.findViewById(R.id.tvId);
        layout = view.findViewById(R.id.layout);
    }

    public static EsportsHolder create(LayoutInflater inflater, ViewGroup parent, OnEsportItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_esport_item, parent, false);
        return new EsportsHolder(view, listener);
    }

    public void bindData(final Article article) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + article.featurePhoto).into(featurephoto);
        tvtitle.setText(article.title);
        tvabout.setText(article.preview);

        Log.e("featurePhoto",article.featurePhoto);
        Log.e("title",article.title);
        Log.e("preview",article.preview);
        Log.e("id", String.valueOf(article.id));

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEsportsClick(article.id);
            }
        });
    }

    public interface OnEsportItemClickListener {
        void onEsportsClick(int id);
    }
}
