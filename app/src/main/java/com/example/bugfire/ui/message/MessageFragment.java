package com.example.bugfire.ui.message;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;

import com.example.bugfire.R;
import com.example.bugfire.adapter.TapMessagePagerAdapter;
import com.example.bugfire.response.ArticleCategoriesResponse;
import com.example.bugfire.service.RetrofitService;
import com.google.android.material.tabs.TabLayout;



import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MessageFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TapMessagePagerAdapter tapMessagePagerAdapter;
    private CompositeDisposable compositeDisposable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_messages, container, false);

        compositeDisposable = new CompositeDisposable();
        tabLayout = view.findViewById(R.id.tabMessageLayout);
        viewPager = view.findViewById(R.id.viewPager);
        tapMessagePagerAdapter = new TapMessagePagerAdapter(getFragmentManager());

        getarticleCategories();
        return view;
    }

    private void getarticleCategories() {
        Log.e("getarticleCategories", "success");

        Disposable subscribe = RetrofitService.getApiEnd().getArticleCategories()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, this::handleError);

        compositeDisposable.add(subscribe);

    }

    private void handleResult(ArticleCategoriesResponse articleCategoriesResponse) {
        Log.e("response","success");

        viewPager.setAdapter(tapMessagePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Log.e("id", String.valueOf(articleCategoriesResponse.articleCategories.get(0).id));
        Log.e("category_name", articleCategoriesResponse.articleCategories.get(0).name);

        Log.e("id", String.valueOf(articleCategoriesResponse.articleCategories.get(1).id));
        Log.e("category_name", articleCategoriesResponse.articleCategories.get(1).name);

        Log.e("id", String.valueOf(articleCategoriesResponse.articleCategories.get(2).id));
        Log.e("category_name", articleCategoriesResponse.articleCategories.get(2).name);

        Log.e("id", String.valueOf(articleCategoriesResponse.articleCategories.get(3).id));
        Log.e("category_name", articleCategoriesResponse.articleCategories.get(3).name);

        Log.e("id", String.valueOf(articleCategoriesResponse.articleCategories.get(4).id));
        Log.e("category_name",articleCategoriesResponse.articleCategories.get(4).name);

        tapMessagePagerAdapter.addItem(articleCategoriesResponse.articleCategories);
        Log.e("size", String.valueOf(articleCategoriesResponse.articleCategories.size()));
    }

    private void handleError(Throwable throwable) {
        Log.e("FeedsFailure", throwable.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        compositeDisposable.clear();
    }

}
