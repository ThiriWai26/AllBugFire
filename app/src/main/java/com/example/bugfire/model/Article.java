package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("id")
    public int id;

    @SerializedName("feature_photo")
    public String featurePhoto;

    @SerializedName("title")
    public String title;

    @SerializedName("preview")
    public String preview;

}
