package com.example.bugfire.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.WeekelyNewsAdapter;
import com.example.bugfire.holder.WeekelyNewsHolder;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekelyNewsFragment extends Fragment implements WeekelyNewsHolder.OnWeekelyNewsItemClickListener {

    private RecyclerView recyclerView;
    private WeekelyNewsAdapter adapter;

    public WeekelyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekely_news, container, false);

//        recyclerView = view.findViewById(R.id.weekelynewsRecyclerView);
//        adapter = new WeekelyNewsAdapter(this);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return  view;
    }

    @Override
    public void onItemClick() {

    }
}

