package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.PCGamesAdapter;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.model.GamesList;
import com.example.bugfire.model.TopicFeedsList;
import com.example.bugfire.response.GamesResponse;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PCGamesDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPCGamesDetailAdapter tapPCGamesDetailAdapter;
    private int id = -1;
    private int categoryId = -1;
    private String type = "games";

    private ImageView profile;
    private TextView txname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcgames_detail);

        init();
    }

    private void init() {

        profile = findViewById(R.id.profile);
        txname = findViewById(R.id.tvName);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapPCGamesDetailAdapter = new TapPCGamesDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPCGamesDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("id", String.valueOf(id));

        Bundle bundle1 = getIntent().getExtras();
        categoryId = bundle1.getInt("pc_categoryId");
        Log.e("pc_categoryId",String.valueOf(categoryId));

        RetrofitService.getApiEnd().getGamesList(categoryId).enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if(response.isSuccessful()) {
                    Log.e("response", "success");


//                    String name= String.valueOf(response.body().gamesList.get(0));
//                    for(int i=1;i<response.body().gamesList.size();i++){
//                        name+=","+response.body().gamesList.get(i);}
//                    txname.setText(name);
//                    Log.e("id", String.valueOf(response.body().gamesList.get(0).id));
//                    Log.e("name", response.body().gamesList.get(0).name);

                }else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });
    }
}
