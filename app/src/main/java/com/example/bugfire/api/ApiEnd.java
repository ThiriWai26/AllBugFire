package com.example.bugfire.api;

import com.example.bugfire.response.ArticleCategoriesResponse;
import com.example.bugfire.response.ArticleDetailResponse;
import com.example.bugfire.response.ArticlesResponse;
import com.example.bugfire.response.GamesResponse;
import com.example.bugfire.response.PlayerResponse;
import com.example.bugfire.response.TeamsResponse;
import com.example.bugfire.response.TopicCategoriesResponse;
import com.example.bugfire.response.FeedsResponse;
import com.example.bugfire.response.NewsDetailResponse;
import com.example.bugfire.response.NewsResponse;
import com.example.bugfire.response.TopicFeedsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEnd {

    @GET("/api/feed_stories")
    Call<FeedsResponse> getFeedList(@Query("page") int page);

    @GET("/api/news_stories")
    Call<NewsResponse> getNewList();

    @FormUrlEncoded
    @POST("/api/news_details")
    Call<NewsDetailResponse> getNewDetail(@Field("id") int id);

    @GET("/api/topic_categories")
    Call<TopicCategoriesResponse> getTopicCategories();

    @FormUrlEncoded
    @POST("/api/sub_categories/games")
    Call<GamesResponse> getGamesList(@Field("category_id") int id);

    @GET("/api/sub_categories/players")
    Call<PlayerResponse> getPlayerList();

    @GET("/api/sub_categories/teams")
    Call<TeamsResponse> getTeamsList();

    @GET("/api/article_categories")
    Call<ArticleCategoriesResponse> getArticleCategories();

    @FormUrlEncoded
    @POST("/api/articles")
    Call<ArticlesResponse> getArticleList(@Field("category_id") int id);

    @FormUrlEncoded
    @POST("/api/article_details")
    Call<ArticleDetailResponse> getArticleDetail(@Field("id") int id);

    @FormUrlEncoded
    @POST("/api/feed_topic")
    Call<TopicFeedsResponse> getTopicFeeds(@Field("type") String type, @Field("id") int id);

}
