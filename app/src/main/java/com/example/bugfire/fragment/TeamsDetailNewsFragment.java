package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.activity.TeamsNewsDetailActivity;
import com.example.bugfire.adapter.PlayersNewAdapter;
import com.example.bugfire.adapter.TeamsNewAdapter;
import com.example.bugfire.holder.TeamsNewHolder;
import com.example.bugfire.model.TopicNewsList;
import com.example.bugfire.response.TopicNewsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsDetailNewsFragment extends Fragment implements TeamsNewHolder.OnTeamsNewClickListener {

    private RecyclerView recyclerView;
    private TeamsNewAdapter adapter;

    private String type = "teams";
    private int id = -1;
    List<TopicNewsList> topicNewsLists = new ArrayList<>();

    public TeamsDetailNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams_news, container, false);

        recyclerView = view.findViewById(R.id.playersnewsRecyclerView);
        adapter = new TeamsNewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("id");
        Log.e("ID", String.valueOf(id));

        getteamNewsList();
        return view;

    }

    private void getteamNewsList() {
        RetrofitService.getApiEnd().getTopicNews(type,id).enqueue(new Callback<TopicNewsResponse>() {
            @Override
            public void onResponse(Call<TopicNewsResponse> call, Response<TopicNewsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().topicNewsList.data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopicNewsResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onTeamsNewClick(int id) {
        Intent intent = new Intent(getContext(), TeamsNewsDetailActivity.class);
        intent.putExtra("id", id);
        Log.e("id", String.valueOf(id));
        startActivity(intent);
    }
}
