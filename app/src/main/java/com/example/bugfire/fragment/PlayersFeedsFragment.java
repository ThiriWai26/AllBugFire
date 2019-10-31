package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.PlayersFeedAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.PlayersFeedHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFeedsFragment extends Fragment implements PlayersFeedHolder.OnPlayersFeedClickListener {

    private RecyclerView recyclerView;
    private PlayersFeedAdapter adapter;

    public PlayersFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players_feeds, container, false);

        recyclerView = view.findViewById(R.id.playersfeedRecyclerView);
        adapter = new PlayersFeedAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onPlayersFeedClick() {

    }
}
