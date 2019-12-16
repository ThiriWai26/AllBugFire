package com.example.bugfire.response;

import com.example.bugfire.model.ArticlesList;
import com.google.gson.annotations.SerializedName;

public class ArticlesResponse {

    @SerializedName("articles")
    public ArticlesList articlesList;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("previous_page_url")
    public String previousPage;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("last_page_url")
    public String lastPage;
}
