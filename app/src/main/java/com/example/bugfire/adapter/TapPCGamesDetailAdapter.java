package com.example.bugfire.adapter;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.PCGamesDetailFeedsFragment;
import com.example.bugfire.fragment.PCGamesDetailNewsFragment;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.TopicCategories;

import java.util.ArrayList;
import java.util.List;

public class TapPCGamesDetailAdapter extends FragmentStatePagerAdapter {

    String tab[] = {"Feeds", "News"};

    public TapPCGamesDetailAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                PCGamesDetailFeedsFragment fragment = new PCGamesDetailFeedsFragment();
                return fragment;

            case 1:
                PCGamesDetailNewsFragment fragment1 = new PCGamesDetailNewsFragment();
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
