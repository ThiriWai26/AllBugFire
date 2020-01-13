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
import com.example.bugfire.activity.DotaDetailActivity;
import com.example.bugfire.adapter.DotaAdapter;
import com.example.bugfire.holder.DotaHolder;
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
public class DotaFragment extends Fragment implements DotaHolder.OnDotaItemClickListener {

    private RecyclerView recyclerView;
    private DotaAdapter adapter;

    List<Article> articleLists = new ArrayList<>();
    private int categoryId = -1;
    private String type = "GAME";
    private CompositeDisposable compositeDisposable;

    public DotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dota2, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.dotaRecyclerView);
        adapter = new DotaAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("dota_categoryId");
        Log.e("dotaId",String.valueOf(categoryId));

        getDotaList();
        return view;
    }

    private void getDotaList() {
        Log.e("getDotaList","success");

        Disposable subscribe = RetrofitService.getApiEnd().getArticleList(categoryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleResult(ArticlesResponse articlesResponse) {
        Log.e("response","success");
        articleLists = articlesResponse.articlesList.data;
        adapter.addItem(articlesResponse.articlesList.data);
        Log.e("Dota_Size", String.valueOf(articleLists.size()));
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable throwable) {
        Log.e("FeedsFailure", throwable.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }

    @Override
    public void onDotaClick(int id) {

        Intent intent = new Intent(getContext(), DotaDetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);
    }
}
