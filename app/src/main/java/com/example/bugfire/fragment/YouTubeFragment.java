package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouTubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String API_KEY = "AIzaSyAuUzBy9ffaLb-31gQ9-SfDvM5u_XZoFE4";
    private static final int RECOVERY_REQUEST = 1 ;

    public YouTubeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_you_tube, container, false);
        youTubePlayerView = view.findViewById(R.id.youtube_playerView);
        youTubePlayerView.initialize(API_KEY , this);

        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.e("InitializeSuccess","success");
        if(!b) {
            youTubePlayer.cueVideo("Q5NT63goPtk");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e("InitializeFailure","fail");

        if (youTubeInitializationResult.isUserRecoverableError()) {
//            youTubeInitializationResult.getErrorDialog(getAdapterPosition(), RECOVERY_REQUEST).show();
        }
        else {
            String error = String.format(youTubeInitializationResult.toString());
//            Toast.makeText(ge, error, Toast.LENGTH_LONG).show();
        }
    }
}
