package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticlesList {

    @SerializedName("data")
    public List<Article> data;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("previous_page_url")
    public String previousPage;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("last_page_url")
    public String lastPage;
}
