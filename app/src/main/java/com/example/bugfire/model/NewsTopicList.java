package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

public class NewsTopicList {

    @SerializedName("title")
    public String title;

    @SerializedName("preview")
    public String preview;

    @SerializedName("feature_photo")
    public String featurephoto;

    @SerializedName("category_name")
    public String name;

    @SerializedName("category_photo")
    public String categoryphoto;

    @SerializedName("id")
    public int id;

}
