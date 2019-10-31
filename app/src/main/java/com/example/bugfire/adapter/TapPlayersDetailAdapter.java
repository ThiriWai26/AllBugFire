package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.PlayersArticlesFragment;
import com.example.bugfire.fragment.PlayersFeedsFragment;
import com.example.bugfire.fragment.PlayersNewsFragment;
import com.example.bugfire.fragment.PlayersVideosFragment;

public class TapPlayersDetailAdapter extends FragmentStatePagerAdapter {
    public TapPlayersDetailAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PlayersFeedsFragment fragment = new PlayersFeedsFragment();
                return fragment;

            case 1:
                PlayersNewsFragment fragment1 = new PlayersNewsFragment();
                return fragment1;

            case 2:
                PlayersArticlesFragment fragment2 = new PlayersArticlesFragment();
                return fragment2;

            case 3:
                PlayersVideosFragment fragment3 = new PlayersVideosFragment();
                return fragment3;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
