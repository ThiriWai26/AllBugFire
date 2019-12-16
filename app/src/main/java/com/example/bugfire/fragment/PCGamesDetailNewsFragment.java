package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.NewsAdapter;
import com.example.bugfire.holder.NewsHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesDetailNewsFragment extends Fragment implements NewsHolder.OnNewsClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;

    public PCGamesDetailNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames_detail_news, container, false);

        recyclerView = view.findViewById(R.id.newsRecyclerView);
        adapter = new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onNewsClick(int i) {

    }
}
