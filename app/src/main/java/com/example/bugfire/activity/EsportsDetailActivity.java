package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.fragment.DotaFragment;
import com.example.bugfire.response.ArticleDetailResponse;
import com.example.bugfire.service.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsportsDetailActivity extends AppCompatActivity {

    private ImageView featurephoto;
    private TextView tvtitle, tvname, tvtime, tvabout;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esports_detail);

        initActivity();
    }

    private void initActivity() {
        featurephoto= findViewById(R.id.featurephoto);
        tvtitle = findViewById(R.id.tvtitle);
        tvname = findViewById(R.id.tvname);
        tvtime = findViewById(R.id.tvtime);
        tvabout = findViewById(R.id.tvabout);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("categoryId",String.valueOf(id));

        getEsportsDetail();
    }

    private void getEsportsDetail() {
        Log.e("getEsportsDetail", "success");

        RetrofitService.getApiEnd().getArticleDetail(id).enqueue(new Callback<ArticleDetailResponse>() {
            @Override
            public void onResponse(Call<ArticleDetailResponse> call, Response<ArticleDetailResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().articleDetail.featurePhoto).into(featurephoto);
                    tvtitle.setText(response.body().articleDetail.title);
                    String name=response.body().articleDetail.categoryName.get(0);
                    for(int i=1;i<response.body().articleDetail.categoryName.size();i++){
                        name+=","+response.body().articleDetail.categoryName.get(i);}
                    tvname.setText(name);
                    tvtime.setText(response.body().articleDetail.date);
                    tvabout.setText(response.body().articleDetail.content);

                    Log.e("feature_photo", response.body().articleDetail.featurePhoto);
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