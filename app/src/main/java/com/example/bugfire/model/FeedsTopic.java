package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedsTopic {

    @SerializedName("data")
    public List<FeedsTopicList> feedTopicList;
}
