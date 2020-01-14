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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


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
    private CompositeDisposable compositeDisposable;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_detail);

        compositeDisposable = new CompositeDisposable();
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
        Disposable subscribe = RetrofitService.getApiEnd().getTopicFeeds(page,type,id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TopicFeedsResponse topicFeedsResponse) {
        Log.e("response", "success");
        if (userFont.equals("z")) {
            tvname.setText(Rabbit.uni2zg(name));
            tvabout.setText(Rabbit.uni2zg(teamName));
        } else {
            tvname.setText(Rabbit.zg2uni(name));
            tvabout.setText(Rabbit.zg2uni(teamName));
        }
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(profile);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
