package com.example.uctc_app.model.local.role;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("dosen_id")
    String user_id;

    @SerializedName("nama_user")
    String name;

    @SerializedName("peran")
    String peran;

    @SerializedName("jabatan")
    String jabatan;

    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
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
