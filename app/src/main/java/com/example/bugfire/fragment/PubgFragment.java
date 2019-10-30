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
import com.example.bugfire.activity.PubgDetailActivity;
import com.example.bugfire.adapter.PubgAdapter;
import com.example.bugfire.holder.PubgHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PubgFragment extends Fragment implements PubgHolder.OnPubgItemClickListener {

    private RecyclerView recyclerView;
    private PubgAdapter adapter;

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
        return view;
    }

    @Override
    public void onPubgClick() {
        Intent intent = new Intent(getContext(), PubgDetailActivity.class);
        startActivity(intent);
    }
}
