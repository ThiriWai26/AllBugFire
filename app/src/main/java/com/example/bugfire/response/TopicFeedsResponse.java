package com.example.bugfire.response;

import com.example.bugfire.model.TopicFeedsList;
import com.google.gson.annotations.SerializedName;

public class TopicFeedsResponse {

    @SerializedName("feed_topic")
    public TopicFeedsList topicFeedsList;
}
