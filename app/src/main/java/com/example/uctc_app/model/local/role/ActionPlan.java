package com.example.uctc_app.model.local.role;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ActionPlan implements Parcelable {
    @SerializedName("actionPlan_id")
    int id;
    @SerializedName("actionPlan_name")
    String name;
    @SerializedName("actionPlan_description")
    String description;
    @SerializedName("program")
    int program;

    public ActionPlan(String name, String description, int program) {
        this.name = name;
        this.description = description;
        this.program = program;
    }

    public int getId() {
        return id;
    }

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

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    protected ActionPlan(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        program = in.readInt();
    }

    public static final Creator<ActionPlan> CREATOR = new Creator<ActionPlan>() {
        @Override
        public ActionPlan createFromParcel(Parcel in) {
            return new ActionPlan(in);
        }

        @Override
        public ActionPlan[] newArray(int size) {
            return new ActionPlan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(program);
    }
}
