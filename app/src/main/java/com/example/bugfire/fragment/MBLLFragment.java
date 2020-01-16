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
import com.example.bugfire.activity.MBLLDetailActivity;
import com.example.bugfire.activity.MobileGamesDetailActivity;
import com.example.bugfire.activity.WeekelyNewsActivity;
import com.example.bugfire.adapter.MBLLAdapter;
import com.example.bugfire.holder.MBLLHolder;
import com.example.bugfire.model.Article;
import com.example.bugfire.response.ArticlesResponse;
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
public class MBLLFragment extends Fragment implements MBLLHolder.OnMBLLItemClickListener {

    private RecyclerView recyclerView;
    private MBLLAdapter adapter;
    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;
    private CompositeDisposable compositeDisposable;
    private int page = 1;
    private String nextPage, previousPage, firstPage, lastPage;

    public MBLLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mbll, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.mbllRecyclerView);
        adapter = new MBLLAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("mbll_categoryId");
        Log.e("mbllId",String.valueOf(categoryId));

        getMBLLList(page);
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
                    getMBLLList(++page);
                }
            }
        });
        return view;
    }

    private void getMBLLList(int page) {
        Log.e("getMBLLList","success");

        Disposable subscribe = RetrofitService.getApiEnd().getArticleList(page,categoryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("MBLLfailure", throwable.toString());
    }

    private void handleResult(ArticlesResponse articlesResponse) {
        Log.e("response","success");
        articleList = articlesResponse.articlesList.data;
        adapter.addItem(articlesResponse.articlesList.data);
        nextPage = articlesResponse.articlesList.nextPage;
        previousPage = articlesResponse.articlesList.previousPage;
        firstPage = articlesResponse.articlesList.firstPage;
        lastPage = articlesResponse.articlesList.lastPage;
        Log.e("Dota_Size", String.valueOf(articleList.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMBLLClick(int id) {
        Intent intent = new Intent(getContext(), MBLLDetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }
}
