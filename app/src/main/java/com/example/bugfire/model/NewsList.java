package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsList {

    @SerializedName("data")
    public List<News> data;
}
