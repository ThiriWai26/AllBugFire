package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.activity.MobileGamesDetailActivity;
import com.example.bugfire.adapter.MobileAdapter;
import com.example.bugfire.holder.MobileHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MobileGamesFragment extends Fragment implements MobileHolder.OnMobileItemClickListener{

    private RecyclerView recyclerView;
    private MobileAdapter adapter;

    public MobileGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mobile_games, container, false);

        recyclerView = view.findViewById(R.id.mobileRecyclerView);
        adapter = new MobileAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onMobileClick() {
        Intent intent = new Intent(getContext(), MobileGamesDetailActivity.class);
        startActivity(intent);
    }
}
