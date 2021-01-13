package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.network.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramRepository {

    private static ProgramRepository programRepository;
    private RetrofitService apiService;
    private static final String TAG = "ProgramRepository";

    private ProgramRepository(String token) {
        Log.d(TAG, "ProgramRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ProgramRepository getInstance(String token) {
        if (programRepository == null) {
            programRepository = new ProgramRepository(token);
        }
        return programRepository;
    }

    public synchronized void resetInstance() {
        if(programRepository != null) {
            programRepository = null;
        }
    }

    public  void deleteProgram(int id){
        apiService.deleteProgram(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    public MutableLiveData<TokenResponse> login(String email, String password) {
        MutableLiveData<TokenResponse> tokenResponse = new MutableLiveData<>();

        apiService.login(email,password).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.code());
                    if (response.code() == 200){
                        if (response.body().getAccessToken() != null){
                            Log.d(TAG, "onResponse: " + response.body().getAccessToken());
                            tokenResponse.postValue(response.body());
                        }
                    }
                }else{
                    Log.d(TAG, "OnResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return tokenResponse;
    }
    public void addProgram(Program program){
        apiService.addProgram(program).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "onResponse:" + response.message());
                Log.d("WIFI SUCCESS", "ADDING PROGRAM");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateProgram(String name, String description, String goal, String date,String status){
        apiService.updateProgram(name, description, goal, date ,status).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }
    public MutableLiveData<List<Program>> myPrograms(String user_id) {
        MutableLiveData<List<Program>> listProgram = new MutableLiveData<>();

        apiService.myPrograms(user_id).enqueue(new Callback<ProgramResponse>() {
            @Override
            public void onResponse(Call<ProgramResponse> call, Response<ProgramResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Program " + response.body().getResults().size());
                        listProgram.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProgramResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listProgram;
    }
    public MutableLiveData<List<Program>> getPrograms() {
        MutableLiveData<List<Program>> listProgram = new MutableLiveData<>();

        apiService.getPrograms().enqueue(new Callback<ProgramResponse>() {
            @Override
            public void onResponse(Call<ProgramResponse> call, Response<ProgramResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Program " + response.body().getResults().size());
                        listProgram.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<ProgramResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listProgram;
    }
}
