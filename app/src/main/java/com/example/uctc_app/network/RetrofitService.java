package com.example.uctc_app.network;

import android.util.Log;

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
import com.example.uctc_app.utils.Constants;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RetrofitService {

    private final Endpoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    private RetrofitService (String token) {
        Log.d(TAG, "RetrofitService: " + token);

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        if (token.equals("")) {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            });
        }else {
            client.addInterceptor(chain -> {
                Request request = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", token)
                        .build();
                return chain.proceed(request);
            });
        }

        api = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(Endpoints.class);

    }

    public static RetrofitService getInstance(String token) {
        if (service == null) {
            service = new RetrofitService(token);
        }else if (!token.equals("")) {
            service = new RetrofitService(token);
        }
        return service;
    }

    public Call<TokenResponse> login(String email, String password){
        return api.login(email, password);
    }

    public Call<UserResponse> getUser() {
        return api.getUser();
    }

    public Call<UsersResponse> getCommittees(int id) {
        return api.getCommittees(id);
    }

    public Call<ProgramResponse> getPrograms() {
        return api.getPrograms();
    }

    public Call<Void> addProgram(Program program){
        return  api.addProgram( program.getName(),program.getDescription(),program.getGoal(),program.getStatus(),program.getDate(), program.getCreated_by());
    }
//    public Call<Void> updateProgram(String name, String description, String goal, String date,String status){
//        return  api.updateProgram( new Program(name, description, goal,status, date));
//    }
    public Call<Void> deleteProgram(int id){
        return api.deleteProgram(id);
    }

    public Call<JsonObject> logout() {
        return api.logout();
    }

    public Call<ActionPlanResponse> getActionPlans(int program_id){
        return api.getActionPlans(program_id);
    }

    public Call<Void> addActionPlan(ActionPlan actionPlan ){
        return api.addActionPlan(actionPlan.getName(),actionPlan.getDescription(),actionPlan.getProgram());
    }
    public Call<Void> updateActionPlan(int id,ActionPlan actionPlan ){
        return api.updateActionPlan(id, actionPlan.getName(), actionPlan.getDescription(), actionPlan.getProgram());
    }

    public Call<Void> deleteActionPlan(int id ){
        return api.deleteActionPlan(id);
    }

    public Call<TasksResponse> getTasks(int action_id){
        return api.getTasks(action_id);
    }
    public Call<TasksResponse> getMyTasks(int user_id){
        return api.getMyTasks(user_id);
    }
    public Call<Void> addTask(Task task){
        return api.addTask(task.getName(),Integer.parseInt(task.getStatus()),task.getDescription(),task.getDate(),task.getAction_plan(),task.getPic());
    }
    public Call<TaskResponse> getTask(int id) {
        return api.getTask(id);
    }

    public Call<Void> deleteTask(int id){
        return api.deleteTask(id);
    }

    public Call<Void> updateTask(int id,Task task){
        return api.updateTasks(id, task.getName(), Integer.parseInt(task.getStatus()), task.getDescription(), task.getDate(), task.getAction_plan(), task.getPic());
    }

    public Call<DocumentationResponse> getDocumentation(int program_id){
        return api.getDocumentation(program_id);
    }

    public  Call<ProgramResponse> myPrograms(String user_id){
        return api.myPrograms(Integer.parseInt(user_id));
    }

}
