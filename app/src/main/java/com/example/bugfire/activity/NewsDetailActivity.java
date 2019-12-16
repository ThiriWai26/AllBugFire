package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.model.NewsDetail;
import com.example.bugfire.response.NewsDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvdate, tvabout;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initActivity();
    }

    private void initActivity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvdate = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("newsId");
        Log.e("newsId",String.valueOf(id));

        getNewsDetail();
    }

    private void getNewsDetail() {
        Log.e("getNewsDetail","success");

        RetrofitService.getApiEnd().getNewDetail(id).enqueue(new Callback<NewsDetailResponse>() {
            @Override
            public void onResponse(Call<NewsDetailResponse> call, Response<NewsDetailResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                        tvtitle.setText(response.body().newsDetail.title);
                        tvname.setText(response.body().newsDetail.categoryName);
                        tvabout.setText(response.body().newsDetail.content);
                        tvdate.setText(response.body().newsDetail.date);
                        Picasso.get().load(RetrofitService.BASE_URL + response.body().newsDetail.featurePhoto).into(featurephoto);

                        Log.e("title", response.body().newsDetail.title);
                        Log.e("category_name", response.body().newsDetail.categoryName);
                        Log.e("content", response.body().newsDetail.content);
                        Log.e("date", response.body().newsDetail.date);
                        Log.e("feature_photo", response.body().newsDetail.featurePhoto);
                    }
                else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<NewsDetailResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });
    }
}
