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
    private String name;
    private String teamName;
    private int Id = -1;
    private String photo;

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
        id = bundle.getInt("id");
        name = bundle.getString("name");
        teamName = bundle.getString("team_name");
        photo = bundle.getString("photo");
        Log.e("id", String.valueOf(id));
        Log.e("Name", name);
        Log.e("TeamName", teamName);
        Log.e("Photo", photo);

        Log.e("getPlayerTitle","success");
        RetrofitService.getApiEnd().getPlayerList().enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    tvname.setText(name);
                    tvabout.setText(teamName);
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(profile);
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
}
