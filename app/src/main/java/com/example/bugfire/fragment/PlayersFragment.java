package com.example.bugfire.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.PlayerAdapter;
import com.example.bugfire.holder.PlayerHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayersFragment extends Fragment implements PlayerHolder.OnPlayerItemClickListener {

    private RecyclerView recyclerView;
    private PlayerAdapter adapter;

    public PlayersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_players, container, false);

        recyclerView = view.findViewById(R.id.playerRecyclerView);
        adapter = new PlayerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onPlayerClick() {

    }
}
