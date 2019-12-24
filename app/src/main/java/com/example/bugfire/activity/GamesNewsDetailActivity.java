package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.response.NewsDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesNewsDetailActivity extends AppCompatActivity {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvdate, tvabout;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_news_detail);

        initActivity();
    }

    private void initActivity() {

        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvdate = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("gamesNewsId");
        Log.e("Id",String.valueOf(id));

        getgamesNewsDetail();
    }

    private void getgamesNewsDetail() {
        Log.e("getNewsDetail","success");

        RetrofitService.getApiEnd().getNewDetail(id).enqueue(new Callback<NewsDetailResponse>() {
            @Override
            public void onResponse(Call<NewsDetailResponse> call, Response<NewsDetailResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    tvtitle.setText(response.body().newsDetail.title);

                    String categoryName=response.body().newsDetail.categoryName.get(0);
                    for(int i=1;i<response.body().newsDetail.categoryName.size();i++){
                        categoryName+=","+response.body().newsDetail.categoryName.get(i);}
                    tvname.setText(categoryName);

                    tvabout.setText(response.body().newsDetail.content);
                    tvdate.setText(response.body().newsDetail.date);
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().newsDetail.featurePhoto).into(featurephoto);
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
