package com.example.bugfire.response;

import com.example.bugfire.model.Feeds;
import com.example.bugfire.model.FeedsList;
import com.google.gson.annotations.SerializedName;

public class FeedsResponse {

    @SerializedName("feed_stories")
    public FeedsList feedsList;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("previous_page_url")
    public String previousPage;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("last_page_url")
    public String lastPage;
}
