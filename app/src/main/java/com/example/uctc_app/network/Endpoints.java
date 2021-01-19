package com.example.uctc_app.network;

import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.response.role.ActionPlanResponse;
import com.example.uctc_app.model.response.role.DocumentationResponse;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TaskResponse;
import com.example.uctc_app.model.response.role.TasksResponse;
import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.model.response.role.UserResponse;
import com.example.uctc_app.model.response.role.UsersResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Endpoints {

    @POST("api-login")
    @FormUrlEncoded
    Call<TokenResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("profile")
    Call<UserResponse> getUser();

    @GET("actions/{id}")
    Call<ActionPlanResponse> getActionPlans(@Path("id") int program_id);

    @POST("actions")
    @FormUrlEncoded
    Call<Void> addActionPlan(@Field("name") String name, @Field("description") String description,
                       @Field("program") int program_id );

    @PUT("actions/{id}")
//    @FormUrlEncoded
//    Call<Void> updateActionPlan(@Path("id") int id, @Body ActionPlan actionPlan);
    Call<Void> updateActionPlan(@Path("id") int id, @Query("name") String name, @Query("description") String description,
                                @Query("program") int program_id );

    @DELETE("actions/{id}")
    Call<Void> deleteActionPlan(@Path("id") int id);

    @GET("tasks/{id}")
    Call<TasksResponse> getTasks(@Path("id") int action_id);

    @GET("user/{id}/tasks")
    Call<TasksResponse> getMyTasks(@Path("id") int user_id);

    @POST("tasks")
    @FormUrlEncoded
    Call<Void> addTask(@Field("name") String name, @Field("status") int status,@Field("description") String description,
                       @Field("due_date") String due_date,@Field("action_plan") int action_plan, @Field("PIC") int pic );

    @PUT("tasks/{id}")
//    @FormUrlEncoded
//    Call<Void> updateTasks(@Path("id") int id, @Body Task task);
    Call<Void> updateTasks(@Path("id") int id, @Query("name") String name, @Query("status") int status,@Query("description") String description,
                            @Query("due_date") String due_date,@Query("action_plan") int action_plan, @Query("PIC") int pic );
    // 3 youtube videos and the 5 availble links show this method
    
    @DELETE("tasks/{id}")
    Call<Void> deleteTask(@Path("id") int id);

    @GET("task/{id}")
    Call<TaskResponse> getTask(@Path("id") int id);

    @GET("programs/{id}")
    Call<ProgramResponse> myPrograms(@Path("id") int user_id);


    @GET("programs")
    Call<ProgramResponse> getPrograms();

    @GET("program/{id}/documentation")
    Call<DocumentationResponse> getDocumentation(@Path("id") int id);

    @POST("programs")
    @FormUrlEncoded
    Call<Void> addProgram(@Field("name") String name, @Field("description")  String description, @Field("goal") String goal, @Field("status") String status,
                          @Field("program_date") String program_date, @Field("created_by") String created_by);

    @PUT("programs")
    @FormUrlEncoded
    Call<Void> updateProgram(@Body Program newProgram);


    @DELETE("programs/{id}")
    Call<Void> deleteProgram(@Path("id") int id);

    @GET("programs/{id}/committees")
    Call<UsersResponse> getCommittees(@Path("id") int program_id);

    @POST("logout")
    Call<JsonObject> logout();

}
