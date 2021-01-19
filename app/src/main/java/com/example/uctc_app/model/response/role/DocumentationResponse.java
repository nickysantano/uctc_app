package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.Documentation;
import com.example.uctc_app.model.local.role.Program;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DocumentationResponse {
    @SerializedName("data")
    private List<Documentation> results;

    public List<Documentation> getResults() {
        return results;
    }
}
