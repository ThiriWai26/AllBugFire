package com.example.bugfire.holder;

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
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class MBLLHolder extends RecyclerView.ViewHolder {

    private OnMBLLItemClickListener listener;
    private ImageView featurephoto;
    private TextView tvtitle, tvabout, tvId;
    private RelativeLayout layout;

    public MBLLHolder(@NonNull View view, OnMBLLItemClickListener listener) {
        super(view);
        this.listener = listener;

        featurephoto = view.findViewById(R.id.featurephoto);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvabout = view.findViewById(R.id.tvabout);
        tvId = view.findViewById(R.id.tvId);
        layout = view.findViewById(R.id.layout);
    }

    public void bindData(final Article article) {

        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + article.featurePhoto).into(featurephoto);
        if (userFont.equals("z")) {
            tvtitle.setText(Rabbit.uni2zg(article.title));
            tvabout.setText(Rabbit.uni2zg(article.preview));
        } else {
            tvtitle.setText(Rabbit.zg2uni(article.title));
            tvabout.setText(Rabbit.zg2uni(article.preview));
        }

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMBLLClick(article.id);
            }
        });


    }

    public static MBLLHolder create(LayoutInflater inflater, ViewGroup parent, OnMBLLItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_mbll_item, parent, false);
        return new MBLLHolder(view, listener);
    }


    public interface OnMBLLItemClickListener {
        void onMBLLClick(int id);
    }
}
