package com.example.bugfire.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.GamesAdapter;
import com.example.bugfire.holder.FeedsImageGridHolder;
import com.example.bugfire.holder.GamesHolder;
import com.example.bugfire.model.FeedsTopicList;
import com.example.bugfire.response.TopicFeedsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PCGamesDetailFeedsFragment extends Fragment implements GamesHolder.OnGamesItemClickListener, FeedsImageGridHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private GamesAdapter adapter;

    private String type = "games";
    private int id = -1;
    List<FeedsTopicList> feedsTopicLists = new ArrayList<>();
    private CompositeDisposable compositeDisposable;
    private String nextPage, previousPage, firstPage, lastPage;
    private int page = 1;
    private int totalPage;

    public PCGamesDetailFeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pcgames_detail_feeds, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.pcgamesdetailnewsRecyclerView);
        adapter = new GamesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("id");
        Log.e("ID", String.valueOf(id));

        getPCGamesFeeds(page);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int childCount = linearLayoutManager.findLastVisibleItemPosition();
                Log.e("firstvisibleItem", String.valueOf(firstVisibleItem));
                Log.e("totalItemCount", String.valueOf(totalItemCount));
                Log.e("childCount", String.valueOf(childCount));

                if(nextPage!=null && (firstVisibleItem+childCount>=totalItemCount)){
                    getPCGamesFeeds(++page);
                }
            }
        });

        return view;
    }

    private void getPCGamesFeeds(int i) {
        Log.e("getPCGamesFeeds","success");

        Disposable subscribe = RetrofitService.getApiEnd().getTopicFeeds(page,type,id)
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
        totalPage = topicFeedsResponse.topicFeedsList.lastPageNumber;
        Log.e("topicfeedtotalPage", String.valueOf(totalPage));
        feedsTopicLists = topicFeedsResponse.topicFeedsList.data;
        adapter.addItem(topicFeedsResponse.topicFeedsList.data);
        nextPage = topicFeedsResponse.topicFeedsList.nextPage;
        previousPage = topicFeedsResponse.topicFeedsList.previousPage;
        firstPage = topicFeedsResponse.topicFeedsList.firstPage;
        lastPage = topicFeedsResponse.topicFeedsList.lastPage;
        Log.e("gameFeeds_Size", String.valueOf(feedsTopicLists.size()));
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }


    @Override
    public void onItemClickListener(ImageView img1) {

    }
}
