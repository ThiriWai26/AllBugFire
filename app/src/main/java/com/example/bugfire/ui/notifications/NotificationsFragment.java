package com.example.bugfire.ui.notifications;

import android.os.Bundle;
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
import com.google.android.material.tabs.TabLayout;

public class NotificationsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notifications, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TapNofificationPagerAdapter tapNofificationPagerAdapter = new TapNofificationPagerAdapter(getFragmentManager());

        viewPager.setAdapter(tapNofificationPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}