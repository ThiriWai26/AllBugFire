package com.example.bugfire.fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.bugfire.R;
import com.example.bugfire.activity.WeekelyNewsActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouTubeFragment extends Fragment implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayerSupportFragment youTubePlayerSupportFragment;
    private static final String API_KEY = "AIzaSyAuUzBy9ffaLb-31gQ9-SfDvM5u_XZoFE4";
    private WeekelyNewsActivity myContext;
    private YouTubePlayer YPlayer;

    public YouTubeFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach(Activity activity) {
//
//        if (activity instanceof WeekelyNewsActivity) {
//            myContext = (WeekelyNewsActivity) activity;
//        }
//        super.onAttach(activity);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_you_tube, container, false);
        youTubePlayerView = view.findViewById(R.id.youtube_playerView);
        youTubePlayerView.initialize(API_KEY, this);

//        Fragment youTubePlayerSupportFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

//        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    YPlayer = youTubePlayer;
//                    YPlayer.setFullscreen(true);
//                    YPlayer.loadVideo("2zNSgSzhBfM");
//                    YPlayer.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
//                // TODO Auto-generated method stub
//
//            }
//        });
        return view;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.e("InitializeSuccess","success");
        if(!b) {
            youTubePlayer.setFullscreen(true);
            youTubePlayer.loadVideo("Q5NT63goPtk");
            youTubePlayer.play();
//            youTubePlayer.cueVideo("Q5NT63goPtk");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.e("InitializeFailure","fail");
    }
}
