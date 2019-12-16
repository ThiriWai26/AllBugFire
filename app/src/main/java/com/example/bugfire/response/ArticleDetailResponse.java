package com.example.bugfire.response;

import com.example.bugfire.model.ArticleDetail;
import com.google.gson.annotations.SerializedName;

public class ArticleDetailResponse {

    @SerializedName("article")
    public ArticleDetail articleDetail;

    @SerializedName("error_message")
    public String errorMessage;
}
