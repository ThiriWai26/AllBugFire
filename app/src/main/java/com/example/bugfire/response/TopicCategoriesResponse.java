package com.example.bugfire.response;

import com.example.bugfire.model.TopicCategories;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopicCategoriesResponse {

    @SerializedName("topic_categories")
    public List<TopicCategories> topicCategories;
}
