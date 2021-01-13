package com.example.uctc_app.network;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.model.response.role.UserResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoints {

    @POST("api-login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("profile")
    Call<UserResponse> getUser();

    @GET("programs")
    Call<ProgramResponse> getPrograms();

    @POST("programs")
    Call<POST> addProgram(@Body POST newProgram);

    @DELETE("programs/{id}")
    Call<Void> deleteProgram(@Path("id") String id);

    @POST("logout")
    Call<JsonObject> logout();

}
