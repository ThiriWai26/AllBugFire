package com.example.bugfire.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

import com.example.bugfire.adapter.WeekelyNewsAdapter;
import com.example.bugfire.fragment.DotaFragment;
import com.example.bugfire.fragment.WeekelyNewsFragment;
import com.example.bugfire.fragment.YouTubeFragment;
import com.example.bugfire.holder.WeekelyNewsHolder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


public class WeekelyNewsActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int RECOVERY_REQUEST = 1;

//    private RecyclerView recyclerView;
//    private WeekelyNewsAdapter adapter;

    private YouTubePlayerView youTubePlayerView;
    private static final String API_KEY = "AIzaSyC52HYcRetoK3kE1zwTiST-oeN5kPJ1Qqw";
    private TabLayout tabLayout;


//    private YouTubePlayer youTubePlayer;
//    private YouTubePlayerFragment youTubePlayerFragment;
//    private static final String API_KEY = "AIzaSyC52HYcRetoK3kE1zwTiST-oeN5kPJ1Qqw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekely_news);

        initAcivity();
    }

    private void initAcivity() {

//        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
//        youTubePlayerFragment.initialize(API_KEY, this);

        youTubePlayerView = findViewById(R.id.youtube_playerView);
        youTubePlayerView.initialize(API_KEY , this);

//        tabLayout = findViewById(R.id.tabs);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Videos"));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if(tabLayout.getSelectedTabPosition() == 0){
//                    Log.e("success","tab1");
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//        recyclerView = findViewById(R.id.weekelynewsRecyclerView);
//        adapter = new WeekelyNewsAdapter(this);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.e("InitializeSuccess","success");

        if(!b) {
            youTubePlayer.cueVideo("iAVuMQM2Dho");
//            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e("InitializeFailure","fail");

        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog((Activity) getApplicationContext(), RECOVERY_REQUEST).show();
        }
        else {
            String error = String.format(youTubeInitializationResult.toString());
            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
        }
    }

    public void onClickDota2(View view) {

//        Intent intent = new Intent(this , .class);
//        startActivity(intent);
//        finish();
    }


}
