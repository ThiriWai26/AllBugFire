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
import com.example.bugfire.activity.EsportsDetailActivity;
import com.example.bugfire.adapter.EsportsAdapter;
import com.example.bugfire.holder.EsportsHolder;
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
public class EsportsFragment extends Fragment implements EsportsHolder.OnEsportItemClickListener {

    private RecyclerView recyclerView;
    private EsportsAdapter adapter;

    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;
    private CompositeDisposable compositeDisposable;

    public EsportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_esports, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.esportRecyclerView);
        adapter = new EsportsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("esport_categoryId");
        Log.e("esportId",String.valueOf(categoryId));

        getEsportsList();
        return view;
    }

    private void getEsportsList() {
        Log.e("getEsportsList","success");

        Disposable subscribe =  RetrofitService.getApiEnd().getArticleList(categoryId)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("Esportsfailure", throwable.toString());
    }

    private void handleResult(ArticlesResponse articlesResponse) {
        Log.e("response","success");
        articleList = articlesResponse.articlesList.data;
        adapter.addItem(articlesResponse.articlesList.data);
        Log.e("esports_Size", String.valueOf(articleList.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onEsportsClick(int id) {
        Intent intent = new Intent(getContext(), EsportsDetailActivity.class);
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
