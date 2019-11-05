package com.example.bugfire.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bugfire.R;

public class StoriesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnStoriesItemClickListener listener;
    private WebView webView;
    String frameVideo = "<html><body><br><iframe width=\"345\" height=\"315\" src=\"https://www.youtube.com/embed/47yJ2XCRLZs\" " +
            "frameborder=\"0\" allowfullscreen></iframe></body></html>";


    public StoriesHolder(@NonNull View itemView, OnStoriesItemClickListener listener) {
        super(itemView);
        this.listener = listener;

        webView = itemView.findViewById(R.id.webview);

//        WebSettings ws = webView.getSettings();
////        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
////        ws.getPluginState();
////        ws.setPluginState(WebSettings.PluginState.ON);
////        ws.setJavaScriptEnabled(true);
////        ws.setJavaScriptCanOpenWindowsAutomatically(true);
////
////        webView.setWebChromeClient(new WebChromeClient() {
////        });
////        webView.loadDataWithBaseURL("https://www.youtube.com/embed/47yJ2XCRLZs/", String.valueOf(webView)
////                , "text/html", "UTF-8", null);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadData(frameVideo, "text/html", "utf-8");


    }

    public static StoriesHolder create(LayoutInflater inflater, ViewGroup parent, OnStoriesItemClickListener listener) {
        View view = inflater.inflate(R.layout.layout_stories_item, parent, false);
        return new StoriesHolder(view, listener);
    }

    public static void bindData() {
    }

    @Override
    public void onClick(View v) {
        listener.onStoriesClick();
    }

    public interface OnStoriesItemClickListener {
        void onStoriesClick();
    }
}
