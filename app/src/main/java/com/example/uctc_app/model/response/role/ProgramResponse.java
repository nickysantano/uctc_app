package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.Program;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgramResponse {
    @SerializedName("data")
    private List<Program> results;

    public List<Program> getResults() {
        return results;
    }
}
