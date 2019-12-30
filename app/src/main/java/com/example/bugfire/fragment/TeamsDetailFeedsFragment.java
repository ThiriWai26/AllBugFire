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
import com.example.bugfire.adapter.TeamAdapter;
import com.example.bugfire.adapter.TeamsFeedAdapter;
import com.example.bugfire.holder.TeamHolder;
import com.example.bugfire.holder.TeamsFeedHolder;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.Teams;
import com.example.bugfire.model.TopicFeedsList;
import com.example.bugfire.response.TeamsResponse;
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
public class TeamsDetailFeedsFragment extends Fragment implements TeamsFeedHolder.OnTeamsFeedClickListener, TeamHolder.OnTeamsItemClickListener {

    private RecyclerView recyclerView;
    private TeamsFeedAdapter adapter;

    private String type = "teams";
    private int id = -1;
    List<TopicFeedsList> topicFeedsList = new ArrayList<>();

    public TeamsDetailFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams_feeds, container, false);

        recyclerView = view.findViewById(R.id.teamsdetailnewsRecyclerView);
        adapter = new TeamsFeedAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("team_id");
        Log.e("ID", String.valueOf(id));

        getteamsdetailfeeds();
        return view;
    }

    private void getteamsdetailfeeds() {
        Log.e("getteamsdetailfeeds","success");
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
        });   }


    @Override
    public void onTeamClick(int id) {

    }
}
