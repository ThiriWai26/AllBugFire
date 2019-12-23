package com.example.bugfire.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

import static android.nfc.tech.MifareUltralight.PAGE_SIZE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedsFragment extends Fragment implements FeedsHolder.OnFeedClickListener {

    private RecyclerView recyclerView;
    private FeedsAdapter adapter;
    List<Feeds> feeds = new ArrayList<>();
    private int page = 1;
    private int totalPage;

    private boolean isLoading = false;
    private int currentPage = 0;
    private int visibleThreadhold = 5;
    private int previousTotalItemCount = 0;
    private int startingPageIndex = 0;

    private int visibleItemCount;
    private int totalItemCount;
    private int inVisiblesItems;

    public FeedsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_feeds, container, false);

        recyclerView = view.findViewById(R.id.feedRecyclerView);
        adapter = new FeedsAdapter(this);
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
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

                if(page<=totalPage){
                    Log.e("pageNumber",String.valueOf(page));
                    getFeedsList(++page);

//                    if (!isLoading() && !isLastPage()) {
//                        if ((visibleItemCount + inVisiblesItems) >= totalItemCount && inVisiblesItems >= 0 && totalItemCount >= PAGE_SIZE) {
//                            loadMoreItems();
//                        }
//                    }
                }

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                inVisiblesItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//                // If the total item count is zero and the previous isn't, assume the
//                // list is invalidated and should be reset back to initial state
//                if (totalItemCount < previousTotalItemCount) {
//                    currentPage = startingPageIndex;
//                    previousTotalItemCount = totalItemCount;
//                    if (totalItemCount == 0) {
//                        isLoading = true; }
//                }
//                // If it's still loading, we check to see if the dataset count has
//                // changed, if so we conclude it has finished loading and update the current page
//                // number and total item count.
//                if (isLoading && (totalItemCount > previousTotalItemCount)) {
//                    isLoading = false;
//                    previousTotalItemCount = totalItemCount;
//                    currentPage++;
//                }
//
//
//                // If it isn't currently loading, we check to see if we have breached
//                // the visibleThreshold and need to reload more data.
//                // If we do need to reload some more data, we execute onLoadMore to fetch the data.
//                if (!isLoading && (inVisiblesItems + visibleItemCount + visibleThreadhold) >= totalItemCount ) {
//                    isLoading = onLoadMore(currentPage + 1, totalItemCount);
//                }
            }

//            private boolean onLoadMore(int i, int totalItemCount) {
//                isLoading = true;
//                currentPage++;
//                return true;
//            }
        });

        return view;
    }

    private void loadMoreItems() {
        isLoading = true;
//        currentPage++;
    }

    private boolean isLastPage() {
        return false;
    }

    private boolean isLoading() {
        return false;
    }

    private void getFeedsList(int page) {

        Log.e("getFeedsList","success");
        RetrofitService.getApiEnd().getFeedList(page).enqueue(new Callback<FeedsResponse>() {
            @Override
            public void onResponse(Call<FeedsResponse> call, Response<FeedsResponse> response) {
                if(response.isSuccessful()){
                    Log.e("response","success");
                    totalPage = response.body().feedsList.lastPageNumber;
                    Log.e("totalPage",String.valueOf(totalPage));
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
