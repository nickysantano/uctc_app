package com.example.uctc_app.model.local.role;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Program implements Parcelable {
    @SerializedName("program_id")
    int program_id;

    @SerializedName("program_title")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("goal")
    String goal;

    @SerializedName("created_by")
    String created_by;

    @SerializedName("prorgram_status")
    String status;

    @SerializedName("program_date")
    String date;

    @SerializedName("thumbnail")
    String thumbnail;

    public Program (){}

    public Program( String name, String description, String goal, String created_by, String status, String date) {
        this.name = name;
        this.description = description;
        this.goal = goal;
        this.created_by = created_by;
        this.status = status;
        this.date = date;
    }

    protected Program(Parcel in) {
        program_id = in.readInt();
        name = in.readString();
        description = in.readString();
        goal = in.readString();
        created_by = in.readString();
        status = in.readString();
        date = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(goal);
        dest.writeString(created_by);
        dest.writeString(status);
        dest.writeString(date);
        dest.writeString(thumbnail);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
