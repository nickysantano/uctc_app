package com.example.uctc_app.model.local.role;

import com.google.gson.annotations.SerializedName;

public class Program {
    @SerializedName("program_title")
    String name;
    @SerializedName("description")
    String description;
    @SerializedName("created_by")
    String created_by;
    @SerializedName("status")
    String status;
    @SerializedName("program_date")
    String date;

    public Program (){}

    public Program(String name, String description, String created_by, String status, String date) {
        this.name = name;
        this.description = description;
        this.created_by = created_by;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
