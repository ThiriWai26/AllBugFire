package com.example.bugfire.ui.message;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapMessagePagerAdapter;
import com.example.bugfire.model.ArticleCategories;
import com.example.bugfire.response.ArticleCategoriesResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapMessagePagerAdapter tapMessagePagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        tabLayout = view.findViewById(R.id.tabMessageLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tapMessagePagerAdapter = new TapMessagePagerAdapter(getFragmentManager());

        getarticleCategories();
        return view;
    }

    private void getarticleCategories() {
        Log.e("getarticleCategories", "success");

        RetrofitService.getApiEnd().getArticleCategories().enqueue(new Callback<ArticleCategoriesResponse>() {
            @Override
            public void onResponse(Call<ArticleCategoriesResponse> call, Response<ArticleCategoriesResponse> response) {
                Log.e("response","success");

                viewPager.setAdapter(tapMessagePagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

                Log.e("id", String.valueOf(response.body().articleCategories.get(0).id));
                Log.e("category_name", response.body().articleCategories.get(0).name);

                Log.e("id", String.valueOf(response.body().articleCategories.get(1).id));
                Log.e("category_name", response.body().articleCategories.get(1).name);

                Log.e("id", String.valueOf(response.body().articleCategories.get(2).id));
                Log.e("category_name", response.body().articleCategories.get(2).name);

                Log.e("id", String.valueOf(response.body().articleCategories.get(3).id));
                Log.e("category_name", response.body().articleCategories.get(3).name);

                Log.e("id", String.valueOf(response.body().articleCategories.get(4).id));
                Log.e("category_name", response.body().articleCategories.get(4).name);

                tapMessagePagerAdapter.addItem(response.body().articleCategories);
                Log.e("size", String.valueOf(response.body().articleCategories.size()));
            }

            @Override
            public void onFailure(Call<ArticleCategoriesResponse> call, Throwable t) {

            }
        });
    }
}
