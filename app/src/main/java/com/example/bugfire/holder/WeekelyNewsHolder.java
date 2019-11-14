package com.example.bugfire.holder;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class WeekelyNewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener, YouTubePlayer.OnInitializedListener {

    private OnWeekelyNewsItemClickListener listener;
    private YouTubePlayerView youTubePlayerView;
    private static final String API_KEY = "AIzaSyC52HYcRetoK3kE1zwTiST-oeN5kPJ1Qqw";

    public WeekelyNewsHolder(@NonNull View itemView, OnWeekelyNewsItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        youTubePlayerView = itemView.findViewById(R.id.youtube_playerView);
        youTubePlayerView.initialize(API_KEY , this);

        itemView.setOnClickListener(this);
    }

    public static WeekelyNewsHolder create(LayoutInflater inflater, ViewGroup parent, OnWeekelyNewsItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_weekelynews_item, parent, false);
        return new WeekelyNewsHolder(view, listener);
    }

    public static void bindData() {

    }

    @Override
    public void onClick(View v) {
        listener.onItemClick();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        Log.e("InitializeSuccess","success");

        if(!b) {
            youTubePlayer.cueVideo("WVaCOFVrJPE");
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

    public interface OnWeekelyNewsItemClickListener {

        void onItemClick();
    }
}
