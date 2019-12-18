package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;

public class PCGamesDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcgames_detail);

        init();
    }

    private void init() {

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        TapPCGamesDetailAdapter tapPCGamesDetailAdapter = new TapPCGamesDetailAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tapPCGamesDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
