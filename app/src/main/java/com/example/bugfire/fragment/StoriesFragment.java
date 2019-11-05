package com.example.bugfire.fragment;


import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.StoriesAdapter;
import com.example.bugfire.holder.StoriesHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoriesFragment extends Fragment implements StoriesHolder.OnStoriesItemClickListener {

    private RecyclerView recyclerView;
    private StoriesAdapter adapter;

    public StoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        recyclerView = view.findViewById(R.id.storiesRecyclerView);
        adapter = new StoriesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    @Override
    public void onStoriesClick() {

    }
}
