package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPlayersDetailAdapter;
import com.example.bugfire.adapter.TapTeamsDetailAdapter;
import com.google.android.material.tabs.TabLayout;

public class TeamsDetailActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapTeamsDetailAdapter tapTeamsDetailAdapter;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_detail);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        tapTeamsDetailAdapter = new TapTeamsDetailAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tapTeamsDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("team_id");
        Log.e("Id", String.valueOf(id));
    }
}
