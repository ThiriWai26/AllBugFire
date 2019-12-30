package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.PlayersFeedsFragment;
import com.example.bugfire.fragment.PlayersNewsFragment;
import com.example.bugfire.fragment.TeamsDetailFeedsFragment;
import com.example.bugfire.fragment.TeamsDetailNewsFragment;

public class TapTeamsDetailAdapter extends FragmentStatePagerAdapter {

    String tab[] = {"Feeds", "News"};

    public TapTeamsDetailAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                TeamsDetailFeedsFragment fragment = new TeamsDetailFeedsFragment();
                return fragment;

            case 1:
                TeamsDetailNewsFragment fragment1 = new TeamsDetailNewsFragment();
                return fragment1;

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
