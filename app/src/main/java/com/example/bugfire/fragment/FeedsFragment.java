package com.example.bugfire.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.holder.FeedsHolder;
/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;


    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        recyclerView = view.findViewById(R.id.feedRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;


    }

}
