package com.example.uctc_app.network;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.response.role.ActionPlanResponse;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TaskResponse;
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
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Endpoints {

    @POST("api-login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("profile")
    Call<UserResponse> getUser();

    @GET("action_plan/{id}")
    Call<ActionPlanResponse> getActionPlans(@Path("id") int program_id);

    @GET("tasks/{id}")
    Call<TaskResponse> getTasks(@Path("id") int action_id);

//    @DELETE("programs/{id}")
//    Call<Void> deleteProgram(@Path("id") String id);

    @GET("programs/{id}")
    Call<ProgramResponse> myPrograms(@Path("id") String user_id);


    @GET("programs")
    Call<ProgramResponse> getPrograms();

    @POST("programs")
    @FormUrlEncoded
    Call<Void> addProgram(@Field("name") String name, @Field("description")  String description,@Field("goal") String goal,
                          @Field("program_date") String program_date, @Field("created_by") String created_by);

    @PUT("programs")
    @FormUrlEncoded
    Call<Void> updateProgram(@Body Program newProgram);


    @DELETE("programs/{id}")
    Call<Void> deleteProgram(@Path("id") int id);

    @POST("logout")
    Call<JsonObject> logout();

}
