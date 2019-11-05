package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.holder.FeedsHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesDetailFeedsFragment extends Fragment implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;

    public PCGamesDetailFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames_detail_feeds, container, false);

        recyclerView = view.findViewById(R.id.pcgamesdetailnewsRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onPCFeeds() {

    }
}
