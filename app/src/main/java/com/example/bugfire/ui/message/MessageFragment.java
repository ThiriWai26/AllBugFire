package com.example.bugfire.ui.message;

import android.os.Bundle;
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
import com.google.android.material.tabs.TabLayout;

public class MessageFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabMessageLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TapMessagePagerAdapter tapMessagePagerAdapter = new TapMessagePagerAdapter(getFragmentManager());

        viewPager.setAdapter(tapMessagePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
