package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.StoriesFragment;
import com.example.bugfire.fragment.WeekelyNewsFragment;

public class TapDashboardAdapter extends FragmentStatePagerAdapter {

    String[] tab = {"Stories", "Weekely News"};

    public TapDashboardAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                StoriesFragment fragment = new StoriesFragment();
                return fragment;

            case 1:
                WeekelyNewsFragment fragment1 = new WeekelyNewsFragment();
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
