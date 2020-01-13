package com.example.bugfire.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class PCGamesDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPCGamesDetailAdapter tapPCGamesDetailAdapter;
    private int id = -1;
    private int categoryId = -1;
    private String type = "games";
    private String name;
    private String teamName;
    private String photo;

    private ImageView profile;
    private TextView txname, txabout;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcgames_detail);

        init();
    }

    private void init() {

        compositeDisposable = new CompositeDisposable();
        profile = findViewById(R.id.profile);
        txname = findViewById(R.id.tvName);
        txabout = findViewById(R.id.tvabout);
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

        Log.e("getGameTitle","success");
        Disposable subscribe = RetrofitService.getApiEnd().getTopicFeeds(type, id)
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

        txname.setText(name);
        txabout.setText(teamName);
        Picasso.get().load(RetrofitService.BASE_URL + "/api/download_image/" + photo).into(profile);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }

}
