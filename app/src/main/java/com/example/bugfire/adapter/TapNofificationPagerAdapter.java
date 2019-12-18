package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.MobileGamesFragment;
import com.example.bugfire.fragment.PCGamesFragment;
import com.example.bugfire.fragment.PlayersFragment;
import com.example.bugfire.fragment.TeamsFragment;
import com.example.bugfire.model.TopicCategories;

import java.util.ArrayList;
import java.util.List;

public class TapNofificationPagerAdapter extends FragmentStatePagerAdapter {

    List<TopicCategories> topicCategories = new ArrayList<>();
    String[] tab = {"PC", "Mobile", "Players", "Teams"};

    public TapNofificationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PCGamesFragment fragment = new PCGamesFragment();
                return fragment;

            case 1:
                MobileGamesFragment fragment1 = new MobileGamesFragment();
                return fragment1;

            case 2:
                PlayersFragment fragment2 = new PlayersFragment();
                return fragment2;

            case 3:
                TeamsFragment fragment3 = new TeamsFragment();
                return fragment3;
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
