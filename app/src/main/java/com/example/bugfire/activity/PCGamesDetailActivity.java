package com.example.bugfire.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.adapter.PlayersFeedAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.PlayersFeedHolder;

public class PCGamesDetailActivity extends AppCompatActivity implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcgames_detail);

        initActivity();
    }

    private void initActivity() {

        recyclerView = findViewById(R.id.pcgamesRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void onPCFeeds() {

    }
}
