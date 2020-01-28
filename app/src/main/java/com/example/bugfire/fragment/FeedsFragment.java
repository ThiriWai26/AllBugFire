package com.example.bugfire.fragment;


import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;
import com.example.bugfire.adapter.FeedsAdapter;
import com.example.bugfire.adapter.FeedsImageGridAdapter;
import com.example.bugfire.holder.FeedsHolder;
import com.example.bugfire.holder.FeedsImageGridHolder;
import com.example.bugfire.model.Feeds;
import com.example.bugfire.response.FeedsResponse;
import com.example.bugfire.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment implements FeedsHolder.OnFeedClickListener, FeedsImageGridHolder.OnItemClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;
    List<Feeds> feeds = new ArrayList<>();
    private int page = 1;
    private int totalPage;
    private String nextPage, previousPage, firstPage, lastPage;
    private CompositeDisposable compositeDisposable;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        compositeDisposable = new CompositeDisposable();
        recyclerView = view.findViewById(R.id.feedRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        getFeedsList(page);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull final RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                int childCount = linearLayoutManager.findLastVisibleItemPosition();
                Log.i("firstvisibleItem", String.valueOf(firstVisibleItem));
                Log.i("lastVisibleItem", String.valueOf(childCount));
                Log.i("totalItemCount",String.valueOf(totalItemCount));

                if(nextPage!=null && (firstVisibleItem+childCount>=totalItemCount)){
                    getFeedsList(++page);
                }
            }
        });
        return view;
    }

    private void getFeedsList(int page) {

         Disposable subscribe = RetrofitService.getApiEnd().getFeedList(page)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult , this::handleError);

         compositeDisposable.add(subscribe);

    }

    private void handleError(Throwable throwable) {
        Log.e("FeedsFailure", throwable.toString());
    }

    private void handleResult(FeedsResponse feedsResponse) {
        Log.e("response", "success");
        totalPage = feedsResponse.feedsList.lastPageNumber;
        Log.e("feedstotalPage", String.valueOf(totalPage));
        feeds = feedsResponse.feedsList.data;
        adapter.addItem(feedsResponse.feedsList.data);
        nextPage = feedsResponse.feedsList.nextPage;
        previousPage = feedsResponse.feedsList.previousPage;
        firstPage = feedsResponse.feedsList.firstPage;
        lastPage = feedsResponse.feedsList.lastPage;
        Log.e("Feeds_Size", String.valueOf(feeds.size()));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPCFeeds(int i) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }

    @Override
    public void onItemClickListener(ImageView imageView) {

    }
}
