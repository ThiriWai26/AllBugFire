package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.PlayersFeedsFragment;
import com.example.bugfire.fragment.PlayersNewsFragment;

public class TapPlayersDetailAdapter extends FragmentStatePagerAdapter {

    String tab[] = {"Feeds", "News", "Articles", "Videos"};
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

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
