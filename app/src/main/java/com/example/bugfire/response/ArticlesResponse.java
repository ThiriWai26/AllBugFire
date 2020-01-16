package com.example.bugfire.response;

import com.example.bugfire.model.ArticlesList;
import com.google.gson.annotations.SerializedName;

public class ArticlesResponse {

    @SerializedName("articles")
    public ArticlesList articlesList;


}
