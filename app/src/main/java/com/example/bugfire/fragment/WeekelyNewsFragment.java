package com.example.bugfire.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.bugfire.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeekelyNewsFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    private static final String API_KEY = "AIzaSyAuUzBy9ffaLb-31gQ9-SfDvM5u_XZoFE4";
    private YouTubePlayerView youTubePlayerView;


    public WeekelyNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekely_news, container, false);

        youTubePlayerView = view.findViewById(R.id.youtube_playerView);
        youTubePlayerView.initialize(API_KEY , this);

        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("URBy9t6e8rY");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
