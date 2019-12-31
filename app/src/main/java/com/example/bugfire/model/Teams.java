package com.example.bugfire.model;

import com.google.gson.annotations.SerializedName;

public class Teams {

    @SerializedName("id")
    public int id;

    @SerializedName("photo")
    public String photo;

    @SerializedName("name")
    public String name;

    @SerializedName("category")
    public String category;
}
