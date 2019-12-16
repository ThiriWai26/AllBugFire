package com.example.bugfire.response;

import com.example.bugfire.model.TopicCategories;
import com.google.gson.annotations.SerializedName;

public class TopicCategoriesResponse {

    @SerializedName("topic_categories")
    public TopicCategories topicCategories;
}
