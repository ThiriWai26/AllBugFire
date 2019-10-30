package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.FeedsFragment;
import com.example.bugfire.fragment.NewsFragment;


public class TabPagerAdapter extends FragmentStatePagerAdapter {

    String[] tab = {"Feeds", "News"};

    public TabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                FeedsFragment fragment = new FeedsFragment();
                return fragment;

            case 1:
                NewsFragment fragment1 = new NewsFragment();
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
