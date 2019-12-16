package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.holder.FeedsHolder;

public class MobileGamesDetailActivity extends AppCompatActivity implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_games_detail);

        initActivity();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.mobilegamesRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onPCFeeds(int i) {

    }
}
