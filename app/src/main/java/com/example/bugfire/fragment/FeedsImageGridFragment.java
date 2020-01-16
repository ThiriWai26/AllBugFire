package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsImageGridAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsImageGridFragment extends Fragment {

    private RecyclerView recyclerView;
    private FeedsImageGridAdapter adapter;

    public FeedsImageGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeds_image_grid, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new FeedsImageGridAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        return  view;
    }

}
