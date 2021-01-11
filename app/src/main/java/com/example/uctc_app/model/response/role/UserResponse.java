package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

    @SerializedName("data")
    private User results;

    public User getResults() {
        return results;
    }

}
