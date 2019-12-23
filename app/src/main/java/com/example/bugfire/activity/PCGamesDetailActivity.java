package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPCGamesDetailAdapter;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;

public class PCGamesDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapPCGamesDetailAdapter tapPCGamesDetailAdapter;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcgames_detail);

        init();
    }

    private void init() {

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapPCGamesDetailAdapter = new TapPCGamesDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapPCGamesDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("id", String.valueOf(id));

    }
}
