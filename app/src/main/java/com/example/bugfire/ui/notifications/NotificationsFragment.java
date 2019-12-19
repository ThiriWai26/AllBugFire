package com.example.bugfire.ui.notifications;

import android.content.Intent;
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

public class NotificationsFragment extends Fragment  implements View.OnClickListener{

    private TapNofificationPagerAdapter tapNofificationPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notifications, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tapNofificationPagerAdapter = new TapNofificationPagerAdapter(getFragmentManager());

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

                    viewPager.setAdapter(tapNofificationPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);

                    Log.e("id", String.valueOf(response.body().topicCategories.get(0).id));
                    Log.e("category_name", response.body().topicCategories.get(0).name);
                    Log.e("type", response.body().topicCategories.get(0).type);

                    Log.e("id", String.valueOf(response.body().topicCategories.get(1).id));
                    Log.e("category_name", response.body().topicCategories.get(1).name);
                    Log.e("type", response.body().topicCategories.get(1).type);

                    Log.e("id", String.valueOf(response.body().topicCategories.get(2).id));
                    Log.e("category_name", response.body().topicCategories.get(2).name);
                    Log.e("type", response.body().topicCategories.get(2).type);

                    Log.e("id", String.valueOf(response.body().topicCategories.get(3).id));
                    Log.e("category_name", response.body().topicCategories.get(3).name);
                    Log.e("type", response.body().topicCategories.get(3).type);

                    tapNofificationPagerAdapter.addItem(response.body().topicCategories);
                    Log.e("size", String.valueOf(response.body().topicCategories.size()));
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<TopicCategoriesResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });



    }

    @Override
    public void onClick(View v) {
    }
}