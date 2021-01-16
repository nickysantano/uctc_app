package com.example.uctc_app.model.local.role;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Task implements Parcelable {
    @SerializedName("task_id")
    int task_id;

    @SerializedName("task_name")
    String name;

    @SerializedName("task_status")
    String status;

    @SerializedName("task_description")
    String description;

    @SerializedName("due_date")
    String date;

    @SerializedName("action_plan")
    int action_plan;

    @SerializedName("PIC")
    int pic;

    public Task(){}

    public Task(String name, String status, String description, String date, int action_plan, int pic) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.date = date;
        this.action_plan = action_plan;
        this.pic = pic;
    }

    protected Task(Parcel in) {
        task_id = in.readInt();
        name = in.readString();
        status = in.readString();
        description = in.readString();
        date = in.readString();
        action_plan = in.readInt();
        pic = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAction_plan() {
        return action_plan;
    }

    public void setAction_plan(int action_plan) {
        this.action_plan = action_plan;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(task_id);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeInt(action_plan);
        dest.writeInt(pic);
    }
}
