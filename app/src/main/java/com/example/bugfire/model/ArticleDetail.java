package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleDetail {

    @SerializedName("feature_photo")
    public String featurePhoto;

    @SerializedName("category_name")
    public List<String> categoryName;

    @SerializedName("date")
    public String date;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;
}
