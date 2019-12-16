package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.response.ArticleDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DotaDetailActivity extends AppCompatActivity {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvtime, tvabout;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dota_detail);

        initActitity();
    }

    private void initActitity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvtime = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("dotaId");
        Log.e("dotaId",String.valueOf(id));

        getDotaDetail();
    }

    private void getDotaDetail() {
        Log.e("getDotaDetail", "success");

        RetrofitService.getApiEnd().getArticleDetail(id).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    Picasso.get().load(RetrofitService.BASE_URL + response.body().articleDetail.featurePhoto).into(featurephoto);
                    tvtitle.setText(response.body().articleDetail.title);
                    tvname.setText(response.body().articleDetail.categoryName);
                    tvtime.setText(response.body().articleDetail.date);
                    tvabout.setText(response.body().articleDetail.content);

                    Log.e("feature_photo", response.body().articleDetail.featurePhoto);
                    Log.e("categoryName", response.body().articleDetail.categoryName);
                    Log.e("date", response.body().articleDetail.date);
                    Log.e("title", response.body().articleDetail.title);
                    Log.e("content", response.body().articleDetail.content);
                }
                else{
                    Log.e("response",response.body().errorMessage);
                }
            }

            @Override
            public void onFailure(Call<ArticleDetailResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
}
