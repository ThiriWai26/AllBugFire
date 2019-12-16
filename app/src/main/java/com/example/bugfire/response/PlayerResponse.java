package com.example.bugfire.response;

import com.example.bugfire.model.PlayerList;
import com.google.gson.annotations.SerializedName;

public class PlayerResponse {

    @SerializedName("players")
    public PlayerList playerList;
}
