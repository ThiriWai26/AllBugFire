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
import com.example.bugfire.activity.NewsDetailActivity;
import com.example.bugfire.activity.PlayersNewsDetailActivity;
import com.example.bugfire.adapter.NewsAdapter;
import com.example.bugfire.adapter.PlayerAdapter;
import com.example.bugfire.adapter.PlayersNewAdapter;
import com.example.bugfire.holder.NewsHolder;
import com.example.bugfire.holder.PlayersNewHolder;
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
public class PlayersNewsFragment extends Fragment implements PlayersNewHolder.OnPlayersNewClickListener{

    private RecyclerView recyclerView;
    private PlayersNewAdapter adapter;

    private String type = "players";
    private int id = -1;
    List<TopicNewsList> topicNewsLists = new ArrayList<>();

    public PlayersNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players_news, container, false);

        recyclerView = view.findViewById(R.id.playersnewsRecyclerView);
        adapter = new PlayersNewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getActivity().getIntent().getExtras();
        id = bundle.getInt("id");
        Log.e("id",String.valueOf(id));
        
        getplayersNewsList();
        return view;
    }

    private void getplayersNewsList() {
        Log.e("getplayersNewsList", "success");

        RetrofitService.getApiEnd().getTopicNews(type,id).enqueue(new Callback<TopicNewsResponse>() {
            @Override
            public void onResponse(Call<TopicNewsResponse> call, Response<TopicNewsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().topicNewsList.data);
                    Log.e("pcgamesDataSize" , String.valueOf(topicNewsLists.size()));
                }
            }

            @Override
            public void onFailure(Call<TopicNewsResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public void onPlayersNewClick(int id) {
        Intent intent = new Intent (getContext(), PlayersNewsDetailActivity.class);
        intent.putExtra("id", id);
        Log.e("id", String.valueOf(id));
        startActivity(intent);
    }
}
