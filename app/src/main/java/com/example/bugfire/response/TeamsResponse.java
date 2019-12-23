package com.example.bugfire.response;

import com.example.bugfire.model.Teams;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {

    @SerializedName("teams")
    public List<Teams> team;
}
