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
import com.example.bugfire.activity.GamesNewsDetailActivity;
import com.example.bugfire.adapter.GamesNewsAdapter;
import com.example.bugfire.adapter.NewsAdapter;
import com.example.bugfire.holder.GamesNewsHolder;
import com.example.bugfire.holder.NewsHolder;
import com.example.bugfire.model.NewsTopicList;
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
public class PCGamesDetailNewsFragment extends Fragment implements GamesNewsHolder.OnGamesNewsItemClickListener{

    private RecyclerView recyclerView;
    private GamesNewsAdapter adapter;

    private String type = "games";
    private int id = -1;
    List<NewsTopicList> newsTopicLists = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public PCGamesDetailNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames_detail_news, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.newsRecyclerView);
        adapter = new GamesNewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("id");
        Log.e("NewsId", String.valueOf(id));

        getPCgamesNews();
        return view;
    }

    private void getPCgamesNews() {
        Log.e("getpcgamesnews", "success");

        Disposable subscribe = RetrofitService.getApiEnd().getTopicNews(type, id)
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
        Log.e("pcgamesnewsDataSize" , String.valueOf(newsTopicLists.size()));
    }

    @Override
    public void onGamesNewsClick(int id) {
        Intent intent = new Intent(getContext(), GamesNewsDetailActivity.class);
        intent.putExtra("gamesNewsId",id);
        Log.e("gamesNewsId", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}
