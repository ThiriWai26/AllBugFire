package com.example.bugfire.api;

import com.example.bugfire.response.ArticleDetailResponse;
import com.example.bugfire.response.ArticlesResponse;
import com.example.bugfire.response.PlayerResponse;
import com.example.bugfire.response.TeamsResponse;
import com.example.bugfire.response.TopicCategoriesResponse;
import com.example.bugfire.response.FeedsResponse;
import com.example.bugfire.response.NewsDetailResponse;
import com.example.bugfire.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEnd {

    @GET("/api/feed_stories")
    Call<FeedsResponse> getFeedList();

    @GET("/api/news_stories")
    Call<NewsResponse> getNewList();

    @POST("/api/news_details")
    Call<NewsDetailResponse> getNewDetail(@Field("id") int id);

    @GET("/api/topic_categories")
    Call<TopicCategoriesResponse> getTopicCategories();

//    @POST("/api/sub_categories/games")
//    Call

    @GET("/api/sub_categories/players")
    Call<PlayerResponse> getPlayerList();

    @GET("/api/sub_categories/teams")
    Call<TeamsResponse> getTeamsList();

    @GET("/api/articles")
    Call<ArticlesResponse> getArticleList();

    @POST("/api/article_details")
    Call<ArticleDetailResponse> getArticleDetail(@Field("id") int id);

}
