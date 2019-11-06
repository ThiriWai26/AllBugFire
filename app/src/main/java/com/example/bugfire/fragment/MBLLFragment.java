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
import com.example.bugfire.activity.MBLLDetailActivity;
import com.example.bugfire.activity.WeekelyNewsActivity;
import com.example.bugfire.adapter.MBLLAdapter;
import com.example.bugfire.holder.MBLLHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MBLLFragment extends Fragment implements MBLLHolder.OnMBLLItemClickListener {

    private RecyclerView recyclerView;
    private MBLLAdapter adapter;
    public MBLLFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mbll, container, false);

        recyclerView = view.findViewById(R.id.mbllRecyclerView);
        adapter = new MBLLAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onMBLLClick() {
        Intent intent = new Intent(getContext(), WeekelyNewsActivity.class);
        startActivity(intent);
    }
}
