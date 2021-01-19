package com.example.uctc_app.model.response.role;

import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActionPlanResponse {
    @SerializedName("data")
    private List<ActionPlan> results;

    public List<ActionPlan> getResults() {
        return results;
    }
}
