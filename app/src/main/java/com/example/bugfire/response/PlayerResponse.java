package com.example.bugfire.response;

import com.example.bugfire.model.PlayerList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerResponse {

    @SerializedName("players")
    public List<PlayerList> playerList;
}
