package com.example.uctc_app.network;

import android.util.Log;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.response.role.ActionPlanResponse;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TaskResponse;
import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.model.response.role.UserResponse;
import com.example.uctc_app.utils.Constants;
import com.google.gson.JsonObject;

import kotlin.text.UStringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

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

    public Call<ProgramResponse> getPrograms() {
        return api.getPrograms();
    }

    public Call<Void> addProgram(Program program){
        return  api.addProgram( program.getName(),program.getDescription(),program.getGoal(),program.getDate(), program.getCreated_by());
    }
    public Call<Void> updateProgram(String name, String description, String goal, String date,String status){
        return  api.updateProgram( new Program(name, description, goal,status, date));
    }
    public Call<Void> deleteProgram(int id){
        return api.deleteProgram(id);
    }

    public Call<JsonObject> logout() {
        return api.logout();
    }

    public Call<ActionPlanResponse> getActionPlans(int program_id){
        return api.getActionPlans(program_id);
    }
    public Call<TaskResponse> getTasks(int action_id){
        return api.getTasks(action_id);
    }

    public  Call<ProgramResponse> myPrograms(String user_id){
        return api.myPrograms(Integer.parseInt(user_id));
    }

}
