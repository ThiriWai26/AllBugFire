package com.example.bugfire.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapNofificationPagerAdapter;
import com.example.bugfire.model.TopicCategories;
import com.example.bugfire.response.TopicCategoriesResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private TabLayout tabLayout;
    List<TopicCategories> topicCategoriesList= new ArrayList<>();
    private TapNofificationPagerAdapter tapNofificationPagerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notifications, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TapNofificationPagerAdapter tapNofificationPagerAdapter = new TapNofificationPagerAdapter(getFragmentManager());

        viewPager.setAdapter(tapNofificationPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        getTopicCategories();
        return view;
    }

    private void getTopicCategories() {
        Log.e("getTopicCategories","success");
        RetrofitService.getApiEnd().getTopicCategories().enqueue(new Callback<TopicCategoriesResponse>() {
            @Override
            public void onResponse(Call<TopicCategoriesResponse> call, Response<TopicCategoriesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                }
            }

            @Override
            public void onFailure(Call<TopicCategoriesResponse> call, Throwable t) {

            }
        });



    }
}