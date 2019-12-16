package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesList {

    @SerializedName("data")
    public List<Article> data;
}
