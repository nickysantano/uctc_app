package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.Task;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TasksResponse {
    @SerializedName("data")
    private List<Task> results;

    public List<Task> getResults() {
        return results;
    }

}
