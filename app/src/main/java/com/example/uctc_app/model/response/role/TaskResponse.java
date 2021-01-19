package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.Task;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskResponse {
    @SerializedName("data")
    private Task result;

    public Task getResults() {
        return result;
    }
}
