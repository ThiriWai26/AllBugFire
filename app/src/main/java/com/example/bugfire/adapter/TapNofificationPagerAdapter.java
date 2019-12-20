package com.example.bugfire.adapter;

import android.os.Bundle;
import android.util.Log;

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

    public Object addItem;
    List<TopicCategories> topicCategories = new ArrayList<>();
//    String[] tab = {"PC", "Mobile", "Players", "Teams"};

    public TapNofificationPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                PCGamesFragment fragment = new PCGamesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pc_categoryId", topicCategories.get(0).id);
                fragment.setArguments(bundle);
                Log.e("pc_categoryId", String.valueOf(topicCategories.get(0).id));
                return fragment;

            case 1:
                MobileGamesFragment fragment1 = new MobileGamesFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("mb_categoryId", topicCategories.get(1).id);
                fragment1.setArguments(bundle1);
                Log.e("mb_categoryId", String.valueOf(topicCategories.get(1).id));
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
        return topicCategories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  topicCategories.get(position).name;
    }

    public void addItem(List<TopicCategories> topicCategoriesList) {

        if (topicCategories.isEmpty()) {
            this.topicCategories = topicCategoriesList;
        } else
            this.topicCategories.addAll(topicCategoriesList);

        notifyDataSetChanged();
    }
}
