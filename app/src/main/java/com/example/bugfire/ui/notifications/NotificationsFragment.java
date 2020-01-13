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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotificationsFragment extends Fragment  implements View.OnClickListener{

    private TapNofificationPagerAdapter tapNofificationPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CompositeDisposable compositeDisposable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notifications, container, false);
        compositeDisposable = new CompositeDisposable();
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tapNofificationPagerAdapter = new TapNofificationPagerAdapter(getFragmentManager());

        getTopicCategories();
        return view;
    }

    private void getTopicCategories() {
        Log.e("getTopicCategories","success");

        Disposable subscribe = RetrofitService.getApiEnd().getTopicCategories()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TopicCategoriesResponse topicCategoriesResponse) {
        Log.e("response","success");

        viewPager.setAdapter(tapNofificationPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Log.e("id", String.valueOf(topicCategoriesResponse.topicCategories.get(0).id));
        Log.e("category_name", topicCategoriesResponse.topicCategories.get(0).name);
        Log.e("type", topicCategoriesResponse.topicCategories.get(0).type);

        Log.e("id", String.valueOf(topicCategoriesResponse.topicCategories.get(1).id));
        Log.e("category_name", topicCategoriesResponse.topicCategories.get(1).name);
        Log.e("type", topicCategoriesResponse.topicCategories.get(1).type);

        Log.e("id", String.valueOf(topicCategoriesResponse.topicCategories.get(2).id));
        Log.e("category_name",topicCategoriesResponse.topicCategories.get(2).name);
        Log.e("type", topicCategoriesResponse.topicCategories.get(2).type);

        Log.e("id", String.valueOf(topicCategoriesResponse.topicCategories.get(3).id));
        Log.e("category_name", topicCategoriesResponse.topicCategories.get(3).name);
        Log.e("type", topicCategoriesResponse.topicCategories.get(3).type);

        tapNofificationPagerAdapter.addItem(topicCategoriesResponse.topicCategories);
        Log.e("size", String.valueOf(topicCategoriesResponse.topicCategories.size()));
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }

}