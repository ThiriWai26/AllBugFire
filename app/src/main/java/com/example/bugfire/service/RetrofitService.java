package com.example.bugfire.service;

import android.support.v4.media.session.MediaSessionCompat;

import com.example.bugfire.api.ApiEnd;
import com.example.bugfire.response.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {

    public static final String BASE_URL = "http://192.168.100.8:8000";

    private static ApiEnd apiEnd;
    private static RetrofitService retrofitService;

    public RetrofitService() {
    }

    public static RetrofitService getInstance() {
        if (retrofitService == null) {
            retrofitService = new RetrofitService();
        }


        return retrofitService;
    }

    public static ApiEnd getApiEnd() {

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request originalRequest = chain.request();

                        Request newRequest = originalRequest.newBuilder()
                                .header("Authorization", "Bearer " + Token.token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                });

        OkHttpClient client = clientBuilder.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit service = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        if (apiEnd == null) {

            apiEnd = service.create(ApiEnd.class);
        }

        return apiEnd;
    }
}
