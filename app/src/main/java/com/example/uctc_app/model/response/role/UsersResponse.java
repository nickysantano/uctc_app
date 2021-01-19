package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {
    @SerializedName("data")
    private List<User> results;

    public List<User> getResults() {
        return results;
    }
}
