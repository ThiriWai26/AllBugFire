package com.example.bugfire.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bugfire.fragment.DotaFragment;
import com.example.bugfire.fragment.MBLLFragment;
import com.example.bugfire.fragment.StoriesFragment;
import com.example.bugfire.fragment.WeekelyNewsFragment;

public class TapDashboardAdapter extends FragmentStatePagerAdapter {

    String[] tab = {"Stories"};

    public TapDashboardAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                StoriesFragment fragment = new StoriesFragment();
                return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
