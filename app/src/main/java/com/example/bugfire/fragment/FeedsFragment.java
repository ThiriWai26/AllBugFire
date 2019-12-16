package com.example.bugfire.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.response.FeedsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;
    List<Feeds> feeds = new ArrayList<>();


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

        getFeedsList();

        return view;
    }

    private void getFeedsList() {

        Log.e("getFeedsList","success");
        RetrofitService.getApiEnd().getFeedList().enqueue(new Callback<FeedsResponse>() {
            @Override
            public void onResponse(Call<FeedsResponse> call, Response<FeedsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    adapter.addItem(response.body().feedsList.data);
                    Log.e("Feeds_Size", String.valueOf(feeds.size()));

                    adapter.notifyDataSetChanged();
                }
                else {
                    Log.e("response","fail");
                }
            }

            @Override
            public void onFailure(Call<FeedsResponse> call, Throwable t) {
                Log.e("failure",t.toString());
            }
        });


    }

    @Override
    public void onPCFeeds(int i) {
    }
}
