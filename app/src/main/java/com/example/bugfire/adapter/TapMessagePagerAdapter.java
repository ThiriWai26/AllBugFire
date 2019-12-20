package com.example.bugfire.adapter;

import android.os.Bundle;
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
import com.example.bugfire.model.Article;
import com.example.bugfire.model.ArticleCategories;

import java.util.ArrayList;
import java.util.List;


public class TapMessagePagerAdapter extends FragmentStatePagerAdapter {

    List<ArticleCategories> articleCategories = new ArrayList<>();

    public TapMessagePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return articleCategories.size();
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                DotaFragment fragment = new DotaFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("dota_categoryId", articleCategories.get(0).id);
                fragment.setArguments(bundle);
                Log.e("dotaId", String.valueOf(articleCategories.get(0).id));
                return fragment;

            case 1:
                CSGOFragment fragment1 = new CSGOFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("csgo_categoryId", articleCategories.get(1).id);
                fragment1.setArguments(bundle1);
                Log.e("csgoId", String.valueOf(articleCategories.get(1).id));
                return fragment1;

            case 2:
                PubgFragment fragment2 = new PubgFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("pubg_categoryId", articleCategories.get(2).id);
                fragment2.setArguments(bundle2);
                Log.e("pubgId", String.valueOf(articleCategories.get(2).id));
                return fragment2;

            case 3:
                MBLLFragment fragment3 = new MBLLFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putInt("mbll_categoryId", articleCategories.get(3).id);
                fragment3.setArguments(bundle3);
                Log.e("mbllId", String.valueOf(articleCategories.get(3).id));
                return fragment3;


            case 4:
                EsportsFragment fragment4 = new EsportsFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putInt("esport_categoryId", articleCategories.get(4).id);
                fragment4.setArguments(bundle4);
                Log.e("esportId", String.valueOf(articleCategories.get(4).id));
                return fragment4;

        }

        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return articleCategories.get(position).name;
    }

    public void addItem(List<ArticleCategories> articleCategoriesList) {

        if (articleCategories.isEmpty()) {
            this.articleCategories = articleCategoriesList;
        } else
            this.articleCategories.addAll(articleCategoriesList);

        notifyDataSetChanged();
    }
}
