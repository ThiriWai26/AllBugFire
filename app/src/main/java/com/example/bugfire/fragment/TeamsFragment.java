package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.activity.TeamsDetailActivity;
import com.example.bugfire.adapter.TeamAdapter;
import com.example.bugfire.holder.TeamHolder;
import com.example.bugfire.model.Teams;
import com.example.bugfire.response.TeamsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment implements TeamHolder.OnTeamsItemClickListener {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    List<Teams> teamsList = new ArrayList<>();

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        recyclerView = view.findViewById(R.id.teamRecyclerView);
        adapter = new TeamAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        getTeamsList();
        return view;
    }

    private void getTeamsList() {
        Log.e("getTeamsList","success");

        RetrofitService.getApiEnd().getTeamsList().enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    teamsList = response.body().team;
                    adapter.addItem(response.body().team);
                    Log.e("TeamsDataSize", String.valueOf(teamsList.size()));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });
    }

    @Override
    public void onTeamClick(int id) {
        Intent intent = new Intent(getContext(), TeamsDetailActivity.class);
        intent.putExtra("team_id", id);
        Log.e("TeamId", String.valueOf(id));
        startActivity(intent);
    }
}
