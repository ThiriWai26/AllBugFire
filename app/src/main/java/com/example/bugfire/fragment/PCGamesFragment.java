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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesFragment extends Fragment implements PCGamesHolder.OnPCItemClickListener {

    private RecyclerView recyclerView;
    private PCGamesAdapter adapter;

    private int categoryId = -1;
    List<GamesList> gamesLists = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public PCGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames, container, false);

        compositeDisposable = new CompositeDisposable();
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

        Disposable subscribe = RetrofitService.getApiEnd().getGamesList(categoryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(GamesResponse gamesResponse) {
        Log.e("response", "success");
        gamesLists = gamesResponse.gamesList;
        adapter.addItem(gamesResponse.gamesList);
        Log.e("Games_Size", String.valueOf(gamesLists.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPCClick(GamesList gamesList) {
        Intent intent = new Intent(getContext(), PCGamesDetailActivity.class);
        intent.putExtra("id", gamesList.id);
        intent.putExtra("name", gamesList.name);
        intent.putExtra("team_name", gamesList.category);
        intent.putExtra("photo", gamesList.photo);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}
