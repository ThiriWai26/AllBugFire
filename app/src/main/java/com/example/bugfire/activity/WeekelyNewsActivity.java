package com.example.bugfire.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

import com.example.bugfire.adapter.WeekelyNewsAdapter;
import com.example.bugfire.holder.WeekelyNewsHolder;
import com.google.android.youtube.player.YouTubeBaseActivity;


public class WeekelyNewsActivity extends YouTubeBaseActivity implements WeekelyNewsHolder.OnWeekelyNewsItemClickListener {

    private RecyclerView recyclerView;
    private WeekelyNewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekely_news);

        initAcivity();
    }

    private void initAcivity() {

        recyclerView = findViewById(R.id.weekelynewsRecyclerView);
        adapter = new WeekelyNewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick() {
        finish();
    }
}
