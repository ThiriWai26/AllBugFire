package com.example.bugfire.response;

import com.example.bugfire.model.Feeds;
import com.example.bugfire.model.FeedsList;
import com.google.gson.annotations.SerializedName;

public class FeedsResponse {

    @SerializedName("feed_stories")
    public FeedsList feedsList;


}
