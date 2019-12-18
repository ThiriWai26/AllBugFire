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
import com.example.bugfire.activity.PubgDetailActivity;
import com.example.bugfire.adapter.PubgAdapter;
import com.example.bugfire.holder.PubgHolder;
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
public class PubgFragment extends Fragment implements PubgHolder.OnPubgItemClickListener {

    private RecyclerView recyclerView;
    private PubgAdapter adapter;

    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;
    private String type = "GAME";

    public PubgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pubg, container, false);

        recyclerView = view.findViewById(R.id.pubgRecyclerView);
        adapter = new PubgAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getPubgList();
        return view;
    }

    private void getPubgList() {
        Log.e("getPubgList","success");

        RetrofitService.getApiEnd().getArticleList(categoryId,type).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().articlesList.data);
                    Log.e("PubgData_Size", String.valueOf(articleList.size()));
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
    public void onPubgClick(int id) {
        Intent intent = new Intent(getContext(), PubgDetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);
    }
}
