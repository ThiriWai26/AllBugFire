package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MobileGamesDetailActivity extends AppCompatActivity  {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPCGamesDetailAdapter tapPCGamesDetailAdapter;
    private int id = -1;
    private String type = "games";
    private TextView tvname, tvabout;
    private ImageView profile;
    private String name;
    private String teamName;
    private String photo;
    private CompositeDisposable compositeDisposable;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_games_detail);
        initActivity();
    }

    private void initActivity() {

        compositeDisposable = new CompositeDisposable();
        tvname = findViewById(R.id.tvName);
        tvabout = findViewById(R.id.tvabout);
        profile = findViewById(R.id.profile);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapPCGamesDetailAdapter = new TapPCGamesDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPCGamesDetailAdapter);
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
        Disposable subscribe = RetrofitService.getApiEnd().getTopicFeeds(page,type, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TopicFeedsResponse topicFeedsResponse) {
        Log.e("response","success");
        tvname.setText(name);
        tvabout.setText(teamName);
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(profile);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
