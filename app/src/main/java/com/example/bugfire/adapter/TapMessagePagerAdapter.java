package com.example.bugfire.adapter;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bugfire.fragment.CSGOFragment;
import com.example.bugfire.fragment.DotaFragment;
import com.example.bugfire.fragment.EsportsFragment;
import com.example.bugfire.fragment.MBLLFragment;
import com.example.bugfire.fragment.PubgFragment;


public class TapMessagePagerAdapter extends FragmentStatePagerAdapter {

    String[] tab = {"Dota 2", "CSGO", "MBLL", "Esports", "Pubg"};

    public TapMessagePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                DotaFragment fragment = new DotaFragment();
                Log.e("Dota","success");
                return fragment;

            case 1:
                CSGOFragment fragment1 = new CSGOFragment();
                Log.e("CSGO","success");
                return fragment1;

            case 2:
                MBLLFragment fragment2 = new MBLLFragment();
                Log.e("MBLL","success");
                return fragment2;

            case 3:
                EsportsFragment fragment3 = new EsportsFragment();
                Log.e("Esport","success");
                return fragment3;

            case 4:
                PubgFragment fragment4 = new PubgFragment();
                Log.e("Pubg","success");
                return fragment4;
        }

        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tab[position];
    }
}
