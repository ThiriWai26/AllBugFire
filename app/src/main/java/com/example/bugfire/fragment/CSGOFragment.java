package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.activity.CSGODetailActivity;
import com.example.bugfire.adapter.CSGOAdapter;
import com.example.bugfire.holder.CSGOHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CSGOFragment extends Fragment implements CSGOHolder.OnCSGOItemClickListener {

    private RecyclerView recyclerView;
    private CSGOAdapter adapter;

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

        return view;
    }

    @Override
    public void onCSGOClick() {
        Intent intent = new Intent(getContext(), CSGODetailActivity.class);
        startActivity(intent);

    }
}
