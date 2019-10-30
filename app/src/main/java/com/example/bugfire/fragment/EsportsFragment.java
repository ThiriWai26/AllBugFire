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
import com.example.bugfire.activity.EsportsDetailActivity;
import com.example.bugfire.adapter.EsportsAdapter;
import com.example.bugfire.holder.EsportsHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsportsFragment extends Fragment implements EsportsHolder.OnEsportItemClickListener {

    private RecyclerView recyclerView;
    private EsportsAdapter adapter;

    public EsportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_esports, container, false);

        recyclerView = view.findViewById(R.id.esportRecyclerView);
        adapter = new EsportsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onEsportsClick() {
        Intent intent = new Intent(getContext(), EsportsDetailActivity.class);
        startActivity(intent);
    }
}
