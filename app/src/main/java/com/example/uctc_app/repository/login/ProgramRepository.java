package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.response.role.ProgramResponse;
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

    public  void deleteProgam(String id){
        apiService.deletePrograms(id).enqueue(new Callback<Void>() {
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
