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
import com.example.bugfire.adapter.PlayersFeedAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.PlayersFeedHolder;
import com.example.bugfire.model.TopicFeedsList;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFeedsFragment extends Fragment implements PlayersFeedHolder.OnPlayersFeedClickListener {

    private RecyclerView recyclerView;
    private PlayersFeedAdapter adapter;

    private String type = "players";
    private int id = -1;
    List<TopicFeedsList> topicFeedsList = new ArrayList<>();

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

        Bundle bundle = getActivity().getIntent().getExtras();
        id = bundle.getInt("categoryId");
        Log.e("id",String.valueOf(id));

        getplayersFeeds();
        return view;
    }

    private void getplayersFeeds() {
        Log.e("getplayersFeeds","success");

        RetrofitService.getApiEnd().getTopicFeeds(type,id).enqueue(new Callback<TopicFeedsResponse>() {
            @Override
            public void onResponse(Call<TopicFeedsResponse> call, Response<TopicFeedsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    adapter.addItem(response.body().topicFeedsList.data);
                    Log.e("pcFeedsDataSize", String.valueOf(topicFeedsList.size()));
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<TopicFeedsResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });
    }

    @Override
    public void onPlayersFeedClick() {

    }
}
