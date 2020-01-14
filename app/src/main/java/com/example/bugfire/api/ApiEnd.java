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
import com.example.bugfire.response.TopicNewsResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEnd {

    @GET("/api/feed_stories")
    Observable<FeedsResponse> getFeedList(@Query("page") int page);

    @GET("/api/news_stories")
    Observable<NewsResponse> getNewList(@Query("page") int page);

    @FormUrlEncoded
    @POST("/api/news_details")
    Observable<NewsDetailResponse> getNewDetail(@Field("id") int id);

    @GET("/api/topic_categories")
    Observable<TopicCategoriesResponse> getTopicCategories();

    @FormUrlEncoded
    @POST("/api/sub_categories/games")
    Observable<GamesResponse> getGamesList(@Field("category_id") int id);

    @GET("/api/sub_categories/players")
    Observable<PlayerResponse> getPlayerList();

    @GET("/api/sub_categories/teams")
    Observable<TeamsResponse> getTeamsList();

    @GET("/api/article_categories")
    Observable<ArticleCategoriesResponse> getArticleCategories();

    @FormUrlEncoded
    @POST("/api/articles")
    Observable<ArticlesResponse> getArticleList(@Field("category_id") int id);

    @FormUrlEncoded
    @POST("/api/article_details")
    Observable<ArticleDetailResponse> getArticleDetail(@Field("id") int id);

    @FormUrlEncoded
    @POST("/api/feed_topic")
    Observable<TopicFeedsResponse> getTopicFeeds(@Query("page") int page, @Field("type") String type, @Field("id") int id);

    @FormUrlEncoded
    @POST("/api/news_topic")
    Observable<TopicNewsResponse> getTopicNews(@Query("page") int page, @Field("type") String type, @Field("id") int id);

}
