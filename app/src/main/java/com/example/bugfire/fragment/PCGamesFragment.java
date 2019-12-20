package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.activity.PCGamesDetailActivity;
import com.example.bugfire.adapter.PCGamesAdapter;
import com.example.bugfire.holder.PCGamesHolder;
import com.example.bugfire.model.GamesList;
import com.example.bugfire.response.GamesResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesFragment extends Fragment implements PCGamesHolder.OnPCItemClickListener {

    private RecyclerView recyclerView;
    private PCGamesAdapter adapter;

    private int categoryId = -1;
    List<GamesList> gamesLists = new ArrayList<>();

    public PCGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames, container, false);

        recyclerView = view.findViewById(R.id.pcRecyclerView);
        adapter = new PCGamesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("pc_categoryId");
        Log.e("pc_categoryId",String.valueOf(categoryId));

        getGamesList();
        return view;
    }

    private void getGamesList() {
        Log.e("getGamesList","success");
        RetrofitService.getApiEnd().getGamesList(categoryId).enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Call<GamesResponse> call, Response<GamesResponse> response) {
                if(response.isSuccessful()) {
                    Log.e("response", "success");
                    adapter.addItem(response.body().gamesList);
                    Log.e("Games_Size", String.valueOf(gamesLists.size()));
                }else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<GamesResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });
    }

    @Override
    public void onPCClick(int i) {
        Intent intent = new Intent(getContext(), PCGamesDetailActivity.class);
        startActivity(intent);

    }
}
