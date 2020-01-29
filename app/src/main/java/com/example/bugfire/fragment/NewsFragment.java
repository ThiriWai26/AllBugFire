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
import android.widget.LinearLayout;

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

    private int page = 1;
    private int totalPage;
    private String nextPage, previousPage, firstPage, lastPage;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        getNewsList(page);

//         if (page <= totalPage) {
//              Log.e("pageNumber", String.valueOf(page));
//               if(nextPage!=null && lastVisibleItemPosition==19 )
//                 getNewsList(++page);
//                }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int childCount = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("firstvisibleItem", String.valueOf(firstVisibleItem));
                Log.i("lastVisibleItem", String.valueOf(childCount));
                Log.i("totalItemCount",String.valueOf(totalItemCount));

                if(nextPage!=null && (firstVisibleItem+childCount>=totalItemCount)){
                    getNewsList(++page);
                }
            }
        });
        return view;
    }

    private void getNewsList(int page) {

        Disposable subscribe = RetrofitService.getApiEnd().getNewList(page)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleResult(NewsResponse newsResponse) {
        Log.e("response","success");
        totalPage = newsResponse.newsList.lastPageNumber;
        Log.e("newstotalPage", String.valueOf(totalPage));
        news = newsResponse.newsList.data;
        adapter.addItem(newsResponse.newsList.data);
        nextPage = newsResponse.newsList.nextPage;
        previousPage=newsResponse.newsList.previousPage;
        firstPage=newsResponse.newsList.firstPage;
        lastPage=newsResponse.newsList.lastPage;
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
