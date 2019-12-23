package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPlayersDetailAdapter;
import com.google.android.material.tabs.TabLayout;

public class PlayerDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPlayersDetailAdapter tapPlayersDetailAdapter;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_detail);

        init();
    }

    private void init() {

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapPlayersDetailAdapter = new TapPlayersDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPlayersDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("categoryId",String.valueOf(id));
    }
}
