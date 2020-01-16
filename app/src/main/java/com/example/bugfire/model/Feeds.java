package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feeds {

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

    @SerializedName("source_id")
    public int sourceId;

    @SerializedName("feedable_id")
    public int feedableId;

    @SerializedName("feedable_type")
    public String feedableType;

    @SerializedName("updated_at")
    public String updatedAt;

    @SerializedName("photo")
    public List<String> photo;
}
