package com.example.bugfire.response;

import com.example.bugfire.model.NewsList;
import com.google.gson.annotations.SerializedName;

public class NewsResponse {

    @SerializedName("news_stories")
    public NewsList newsList;

}
