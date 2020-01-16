package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedsTopicList {

    @SerializedName("category_name")
    public String name;

    @SerializedName("category_photo")
    public String categoryPhoto;

    @SerializedName("source_logo")
    public String sourceLogo;

    @SerializedName("date")
    public String date;

    @SerializedName("content")
    public String content;

    @SerializedName("photos")
    public List<String> photo;

}
