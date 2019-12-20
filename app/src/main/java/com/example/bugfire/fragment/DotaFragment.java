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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotaFragment extends Fragment implements DotaHolder.OnDotaItemClickListener {

    private RecyclerView recyclerView;
    private DotaAdapter adapter;

    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;
    private String type = "GAME";

    public DotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dota2, container, false);

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

        RetrofitService.getApiEnd().getArticleList(categoryId).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().articlesList.data);
                    Log.e("DotaData_Size", String.valueOf(articleList.size()));
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });

    }

    @Override
    public void onDotaClick(int id) {

        Intent intent = new Intent(getContext(), DotaDetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);
    }
}
