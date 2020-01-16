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
import com.example.bugfire.activity.GamesNewsDetailActivity;
import com.example.bugfire.adapter.GamesNewsAdapter;
import com.example.bugfire.holder.GamesNewsHolder;
import com.example.bugfire.model.NewsTopicList;
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
    private String nextPage, previousPage, firstPage, lastPage;
    private int page = 1;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("id");
        Log.e("NewsId", String.valueOf(id));

        getPCgamesNews(page);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int childCount = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("firstvisibleItem", String.valueOf(firstVisibleItem));
                Log.i("lastVisibleItem", String.valueOf(childCount));
                Log.i("totalItemCount",String.valueOf(totalItemCount));

                if(nextPage!=null && (firstVisibleItem+childCount >= totalItemCount) ){
                    getPCgamesNews(++page);
                }

            }
        });

        return view;
    }

    private void getPCgamesNews(int i) {
        Log.e("getpcgamesnews", "success");

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
