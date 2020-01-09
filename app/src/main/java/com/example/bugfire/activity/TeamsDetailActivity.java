package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapTeamsDetailAdapter;
import com.example.bugfire.rabbitconverter.Rabbit;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bugfire.activity.FontStatusActivity.userFont;

public class TeamsDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapTeamsDetailAdapter tapTeamsDetailAdapter;
    private int id = -1;
    private String type = "teams";
    private String name;
    private String teamName;
    private String photo;

    private TextView tvname, tvabout;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_detail);

        tvname = findViewById(R.id.tvName);
        tvabout = findViewById(R.id.tvabout);
        profile = findViewById(R.id.profile);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapTeamsDetailAdapter = new TapTeamsDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapTeamsDetailAdapter);
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

        Log.e("getGameTitle", "success");
        RetrofitService.getApiEnd().getTopicFeeds(type, id).enqueue(new Callback<TopicFeedsResponse>() {
            @Override
            public void onResponse(Call<TopicFeedsResponse> call, Response<TopicFeedsResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("response", "success");
                    if (userFont.equals("z")) {
                        tvname.setText(Rabbit.uni2zg(name));
                        tvabout.setText(Rabbit.uni2zg(teamName));
                    } else {
                        tvname.setText(Rabbit.zg2uni(name));
                        tvabout.setText(Rabbit.zg2uni(teamName));
                    }
                    Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(profile);
                } else {
                    Log.e("response", "fail");
                }
            }

            @Override
            public void onFailure(Call<TopicFeedsResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }
}
