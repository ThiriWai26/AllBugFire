package com.example.bugfire.response;

import com.example.bugfire.model.GamesList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GamesResponse {

    @SerializedName("games")
    public List<GamesList> gamesList;
}
