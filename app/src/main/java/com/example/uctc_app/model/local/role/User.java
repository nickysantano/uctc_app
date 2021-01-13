package com.example.uctc_app.model.local.role;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    int user_id;

    @SerializedName("name")
    String name;

    @SerializedName("role_id")
    String role_id;

    @SerializedName("department_id")
    String department_id;

    @SerializedName("phone_number")
    String phone_number;

    @SerializedName("status")
    String status;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
