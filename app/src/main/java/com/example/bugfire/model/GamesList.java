package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

public class GamesList {

    @SerializedName("photo")
    public String photo;

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("category")
    public String category;
}
