package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TeamAdapter;
import com.example.bugfire.adapter.TeamsFeedAdapter;
import com.example.bugfire.holder.TeamHolder;
import com.example.bugfire.holder.TeamsFeedHolder;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.model.Teams;
import com.example.bugfire.model.TopicFeedsList;
import com.example.bugfire.response.TeamsResponse;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsDetailFeedsFragment extends Fragment implements TeamsFeedHolder.OnTeamsFeedClickListener {

    private RecyclerView recyclerView;
    private TeamsFeedAdapter adapter;

    private String type = "teams";
    private int id = -1;
    List<TopicFeedsList> topicFeedsList = new ArrayList<>();
    private CompositeDisposable compositeDisposable;

    public TeamsDetailFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams_feeds, container, false);

        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.teamsdetailnewsRecyclerView);
        adapter = new TeamsFeedAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("id");
        Log.e("ID", String.valueOf(id));

        getteamsdetailfeeds();
        return view;
    }

    private void getteamsdetailfeeds() {
        Log.e("getteamsdetailfeeds","success");

        Disposable subscribe = RetrofitService.getApiEnd().getTopicFeeds(type, id)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);
    }

    private void handleError(Throwable throwable) {
        Log.e("failure", throwable.toString());
    }

    private void handleResult(TopicFeedsResponse topicFeedsResponse) {
        Log.e("response","success");

        adapter.addItem(topicFeedsResponse.topicFeedsList.data);
        Log.e("pcFeedsDataSize", String.valueOf(topicFeedsList.size()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }


}
