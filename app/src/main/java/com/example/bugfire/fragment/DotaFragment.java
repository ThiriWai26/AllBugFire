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
import com.example.bugfire.activity.DotaDetailActivity;
import com.example.bugfire.adapter.DotaAdapter;
import com.example.bugfire.holder.DotaHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DotaFragment extends Fragment implements DotaHolder.OnDotaItemClickListener {

    private RecyclerView recyclerView;
    private DotaAdapter adapter;

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

        return view;
    }

    @Override
    public void onDotaClick() {

        Intent intent = new Intent(getContext(), DotaDetailActivity.class);
        startActivity(intent);
    }
}
