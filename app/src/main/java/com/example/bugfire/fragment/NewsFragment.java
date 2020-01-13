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
import com.example.bugfire.adapter.NewsAdapter;
import com.example.bugfire.holder.NewsHolder;
import com.example.bugfire.model.News;
import com.example.bugfire.response.NewsResponse;
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
public class NewsFragment extends Fragment implements NewsHolder.OnNewsClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    List<News> news = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getNewsList();
        return view;
    }

    private void getNewsList() {

        Disposable subscribe = RetrofitService.getApiEnd().getNewList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleResult(NewsResponse newsResponse) {
        Log.e("response","success");
        news = newsResponse.newsList.data;
        adapter.addItem(newsResponse.newsList.data);
        Log.e("NewsList_size", String.valueOf(news.size()));

        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable throwable) {
        Log.e("NewsFailure",throwable.toString());
    }

    @Override
    public void onNewsClick(int id) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra("newsId",id);
        Log.e("news_Id", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}
