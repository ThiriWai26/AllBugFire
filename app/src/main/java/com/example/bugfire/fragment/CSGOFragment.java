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
import com.example.bugfire.activity.CSGODetailActivity;
import com.example.bugfire.adapter.CSGOAdapter;
import com.example.bugfire.holder.CSGOHolder;
import com.example.bugfire.model.Article;
import com.example.bugfire.response.ArticlesResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CSGOFragment extends Fragment implements CSGOHolder.OnCSGOItemClickListener {

    private RecyclerView recyclerView;
    private CSGOAdapter adapter;

    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;
    private CompositeDisposable compositeDisposable;
    private int page = 1;
    private String nextPage, previousPage, firstPage, lastPage;

    public CSGOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_csgo, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.csgoRecyclerView);
        adapter = new CSGOAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("csgo_categoryId");
        Log.e("csgoId",String.valueOf(categoryId));

        getCSGOList(page);
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
                    getCSGOList(++page);
                }
            }
        });

        return view;
    }

    private void getCSGOList(int page) {
        Log.e("getCSGOList","success");

        Disposable subscribe = RetrofitService.getApiEnd().getArticleList(page,categoryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handlerResult, this::handlerError);

        compositeDisposable.add(subscribe);

    }

    private void handlerError(Throwable throwable) {
        Log.e("FeedsFailure", throwable.toString());
    }

    private void handlerResult(ArticlesResponse articlesResponse) {
        Log.e("response","success");
        articleList = articlesResponse.articlesList.data;
        adapter.addItem(articlesResponse.articlesList.data);
        nextPage = articlesResponse.articlesList.nextPage;
        previousPage = articlesResponse.articlesList.previousPage;
        firstPage = articlesResponse.articlesList.firstPage;
        lastPage = articlesResponse.articlesList.lastPage;
        Log.e("CSGO_Size", String.valueOf(articleList.size()));
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }


    @Override
    public void onCSGOClick(int id) {
        Intent intent = new Intent(getContext(), CSGODetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);

    }
}
