package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicFeedsList {

    @SerializedName("data")
    public List<TopicFeeds> data;
}
