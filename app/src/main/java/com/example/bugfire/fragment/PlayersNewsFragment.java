package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersNewsFragment extends Fragment implements PlayersNewHolder.OnPlayersNewClickListener{

    private RecyclerView recyclerView;
    private PlayersNewAdapter adapter;

    private String type = "players";
    private int id = -1;
    List<TopicNewsList> topicNewsLists = new ArrayList<>();
    private CompositeDisposable compositeDisposable;
    private String nextPage, previousPage, firstPage, lastPage;
    private int lastVisibleItemPosition = 0;
    private int page = 1;

    public PlayersNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players_news, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.playersnewsRecyclerView);
        adapter = new PlayersNewAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getActivity().getIntent().getExtras();
        id = bundle.getInt("id");
        Log.e("id",String.valueOf(id));
        
        getplayersNewsList(page);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int FirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("firstvisibleItem", String.valueOf(FirstVisibleItem));
                Log.i("lastVisibleItem", String.valueOf(lastVisibleItemPosition));
                Log.i("totalItemCount",String.valueOf(totalItemCount));

                if(nextPage!=null && lastVisibleItemPosition==19 ){
                    getplayersNewsList(++page);
                }
            }
        });
        return view;
    }

    private void getplayersNewsList(int page) {
        Log.e("getplayersNewsList", "success");

        Disposable subscribe = RetrofitService.getApiEnd().getTopicNews(page,type, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TopicNewsResponse topicNewsResponse) {
        Log.e("response","success");
        adapter.addItem(topicNewsResponse.topicNewsList.data);
        nextPage = topicNewsResponse.topicNewsList.nextPage;
        previousPage = topicNewsResponse.topicNewsList.previousPage;
        firstPage = topicNewsResponse.topicNewsList.firstPage;
        lastPage = topicNewsResponse.topicNewsList.lastPage;
        Log.e("playergamesDataSize" , String.valueOf(topicNewsLists.size()));
    }


    @Override
    public void onPlayersNewClick(int id) {
        Intent intent = new Intent (getContext(), PlayersNewsDetailActivity.class);
        intent.putExtra("id", id);
        Log.e("id", String.valueOf(id));
        startActivity(intent);
    }
}
