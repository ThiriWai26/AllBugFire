package com.example.bugfire.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.activity.PCGamesDetailActivity;
import com.example.bugfire.adapter.PCGamesAdapter;
import com.example.bugfire.holder.PCGamesHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesFragment extends Fragment implements PCGamesHolder.OnPCItemClickListener {

    private RecyclerView recyclerView;
    private PCGamesAdapter adapter;

    public PCGamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames, container, false);

        recyclerView = view.findViewById(R.id.pcRecyclerView);
        adapter = new PCGamesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onPCClick() {
        Intent intent = new Intent(getContext(), PCGamesDetailActivity.class);
        startActivity(intent);
    }
}
