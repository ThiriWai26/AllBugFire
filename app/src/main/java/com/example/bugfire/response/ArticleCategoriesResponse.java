package com.example.bugfire.response;

import com.example.bugfire.model.ArticleCategories;
import com.google.gson.annotations.SerializedName;

public class ArticleCategoriesResponse {

    @SerializedName("article_categories")
    public ArticleCategories articleCategories;

}
