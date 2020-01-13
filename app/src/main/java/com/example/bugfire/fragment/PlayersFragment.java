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
import com.example.bugfire.activity.PlayerDetailActivity;
import com.example.bugfire.adapter.PlayerAdapter;
import com.example.bugfire.holder.PlayerHolder;
import com.example.bugfire.model.PlayerList;
import com.example.bugfire.response.PlayerResponse;
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
public class PlayersFragment extends Fragment implements PlayerHolder.OnPlayerItemClickListener {

    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    List<PlayerList> playerList = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.playerRecyclerView);
        adapter = new PlayerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        getPlayerList();
        return view;

    }

    private void getPlayerList() {
        Log.e("getPlayerList","success");

        Disposable subscribe = RetrofitService.getApiEnd().getPlayerList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(PlayerResponse playerResponse) {
        Log.e("response","success");
        playerList= playerResponse.playerList;
        adapter.addItem(playerResponse.playerList);
        Log.e("player_Size", String.valueOf(playerList.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPlayerClick(PlayerList playerList) {
        Log.e("success","done");
        Intent intent = new Intent(getContext(), PlayerDetailActivity.class);
        intent.putExtra("id",playerList.id);
        intent.putExtra("name",playerList.name);
        intent.putExtra("team_name",playerList.teamName);
        intent.putExtra("photo",playerList.photo);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }

}
