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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment implements TeamHolder.OnTeamsItemClickListener {

    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    List<Teams> teamsList = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.teamRecyclerView);
        adapter = new TeamAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

        getTeamsList();
        return view;
    }

    private void getTeamsList() {
        Log.e("getTeamsList","success");

        Disposable subscribe = RetrofitService.getApiEnd().getTeamsList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TeamsResponse teamsResponse) {
        Log.e("response","success");
        teamsList = teamsResponse.team;
        adapter.addItem(teamsResponse.team);
        Log.e("TeamsDataSize", String.valueOf(teamsList.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onTeamClick(Teams teams) {
        Intent intent = new Intent(getContext(), TeamsDetailActivity.class);
        intent.putExtra("id", teams.id);
        intent.putExtra("name", teams.name);
        intent.putExtra("team_name", teams.category);
        intent.putExtra("photo", teams.photo);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}
