package com.example.bugfire.response;

import com.example.bugfire.model.TopicNewsList;
import com.google.gson.annotations.SerializedName;

public class TopicNewsResponse {

    @SerializedName("news_topic")
    public TopicNewsList topicNewsList;
}
