package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPlayersDetailAdapter;
import com.example.bugfire.response.PlayerResponse;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPlayersDetailAdapter tapPlayersDetailAdapter;
    private int id = -1;
    private int categoryId = -1;
    private String type = "players";

    private TextView tvname, tvabout;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        init();
    }

    private void init() {

        tvname = findViewById(R.id.tvName);
        tvabout = findViewById(R.id.tvabout);
        profile = findViewById(R.id.profile);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapPlayersDetailAdapter = new TapPlayersDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPlayersDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("categoryId", String.valueOf(id));

        Log.e("getPlayerTitle","success");
        RetrofitService.getApiEnd().getTopicFeeds(type,id).enqueue(new Callback<TopicFeedsResponse>() {
            @Override
            public void onResponse(Call<TopicFeedsResponse> call, Response<TopicFeedsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    tvname.setText(response.body().topicFeedsList.data.get(0).name);
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + response.body().topicFeedsList.data.get(0).categoryPhoto).into(profile);

                    Log.e("Name", response.body().topicFeedsList.data.get(0).name);
                    Log.e("Photo", response.body().topicFeedsList.data.get(0).categoryPhoto);
                    }
                    else {
                        Log.e("response","fail");
                    }
                }

                @Override
                public void onFailure(Call<TopicFeedsResponse> call, Throwable t) {
                    Log.e("failure", t.toString());
                }
            });
    }
}
