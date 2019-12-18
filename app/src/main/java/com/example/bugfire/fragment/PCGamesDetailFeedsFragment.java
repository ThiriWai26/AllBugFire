package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.adapter.GamesAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.GamesHolder;
import com.example.bugfire.service.RetrofitService;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesDetailFeedsFragment extends Fragment implements GamesHolder.OnGamesItemClickListener {

    private RecyclerView recyclerView;
    private GamesAdapter adapter;

    private String type = "GAME";
    private int id = -1;

    public PCGamesDetailFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames_detail_feeds, container, false);

        recyclerView = view.findViewById(R.id.pcgamesdetailnewsRecyclerView);
        adapter = new GamesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPCGamesFeeds();
        return view;
    }

    private void getPCGamesFeeds() {
        Log.e("getPCGamesFeeds","success");

//        RetrofitService.getApiEnd().getTopicFeeds()
    }


}
