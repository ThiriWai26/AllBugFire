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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements NewsHolder.OnNewsClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    List<News> news = new ArrayList<>();

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getNewsList();
        return view;
    }

    private void getNewsList() {
        Log.e("getNewsList","success");

        RetrofitService.getApiEnd().getNewList().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    news = response.body().newsList.data;
                    adapter.addItem(response.body().newsList.data);
                    Log.e("NewsList_size", String.valueOf(news.size()));

                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsFailure",t.toString());
            }
        });
    }

    @Override
    public void onNewsClick(int id) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra("newsId",id);
        Log.e("news_Id", String.valueOf(id));
        startActivity(intent);
    }
}
