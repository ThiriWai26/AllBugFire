package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.activity.PlayersDetailActivity;
import com.example.bugfire.adapter.PlayerAdapter;
import com.example.bugfire.holder.PlayerHolder;
import com.example.bugfire.model.PlayerList;
import com.example.bugfire.response.PlayerResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment implements PlayerHolder.OnPlayerItemClickListener {

    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    List<PlayerList> playerList = new ArrayList<>();

    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        recyclerView = view.findViewById(R.id.playerRecyclerView);
        adapter = new PlayerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        getPlayerList();
        return view;

    }

    private void getPlayerList() {
        Log.e("getPlayerList","success");
        RetrofitService.getApiEnd().getPlayerList().enqueue(new Callback<PlayerResponse>() {
            @Override
            public void onResponse(Call<PlayerResponse> call, Response<PlayerResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().playerList);
                    Log.e("PlayerList_Size", String.valueOf(playerList.size()));

                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<PlayerResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });

    }

    @Override
    public void onPlayerClick(int i) {
        Log.e("success","done");
        Intent intent = new Intent(getContext(), PlayersDetailActivity.class);
        startActivity(intent);
    }

}
