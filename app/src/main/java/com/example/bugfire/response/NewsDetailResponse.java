package com.example.bugfire.response;

import com.example.bugfire.model.NewsDetail;
import com.google.gson.annotations.SerializedName;

public class NewsDetailResponse {

    @SerializedName("news")
    public NewsDetail newsDetail;

    @SerializedName("error_message")
    public String errorMessage;

}
