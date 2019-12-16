package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

public class NewsDetail {

    @SerializedName("feature_photo")
    public String featurePhoto;

    @SerializedName("category_name")
    public String categoryName;

    @SerializedName("date")
    public String date;

    @SerializedName("title")
    public String title;

    @SerializedName("content")
    public String content;
}
