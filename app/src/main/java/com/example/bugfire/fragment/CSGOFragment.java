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
import com.example.bugfire.activity.CSGODetailActivity;
import com.example.bugfire.adapter.CSGOAdapter;
import com.example.bugfire.holder.CSGOHolder;
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
public class CSGOFragment extends Fragment implements CSGOHolder.OnCSGOItemClickListener {

    private RecyclerView recyclerView;
    private CSGOAdapter adapter;

    List<Article> articleList = new ArrayList<>();
    private int categoryId = -1;

    public CSGOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_csgo, container, false);

        recyclerView = view.findViewById(R.id.csgoRecyclerView);
        adapter = new CSGOAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle bundle = getArguments();
        categoryId = bundle.getInt("csgo_categoryId");
        Log.e("csgoId",String.valueOf(categoryId));

        getCSGOList();
        return view;
    }

    private void getCSGOList() {
        Log.e("getCSGOList","success");

        RetrofitService.getApiEnd().getArticleList(categoryId).enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(Call<ArticlesResponse> call, Response<ArticlesResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");

                    adapter.addItem(response.body().articlesList.data);
                    Log.e("CSGOData_Size", String.valueOf(articleList.size()));
                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<ArticlesResponse> call, Throwable t) {
                Log.e("failure", t.toString());
            }
        });


    }

    @Override
    public void onCSGOClick(int id) {
        Intent intent = new Intent(getContext(), CSGODetailActivity.class);
        intent.putExtra("categoryId",id);
        Log.e("categoryId", String.valueOf(id));
        startActivity(intent);

    }
}
