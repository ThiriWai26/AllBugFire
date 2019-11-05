package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapPlayersDetailAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersDetailFragment extends Fragment implements View.OnClickListener {


    public PlayersDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_players_detail, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        TapPlayersDetailAdapter tapPlayersDetailAdapter = new TapPlayersDetailAdapter(getFragmentManager());
//
        viewPager.setAdapter(tapPlayersDetailAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }



    @Override
    public void onClick(View v) {
//        Log.e("success","done");
//        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
    }
}
