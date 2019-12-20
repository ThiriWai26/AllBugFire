package com.example.bugfire.response;

import com.example.bugfire.model.ArticleCategories;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleCategoriesResponse {

    @SerializedName("article_categories")
    public List<ArticleCategories> articleCategories;

}
