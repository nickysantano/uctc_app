package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.network.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private static ProfileRepository profileRepository;
    private RetrofitService apiService;
    private static final String TAG = "ProfileRepository";

    private ProfileRepository(String token) {
        Log.d(TAG, "ProfileRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ProfileRepository getInstance(String token) {
        if (profileRepository == null) {
            profileRepository = new ProfileRepository(token);
        }
        return profileRepository;
    }

    public synchronized void resetInstance() {
        if(profileRepository != null) {
            profileRepository = null;
        }
    }

    //should be mutable code to get profile but not already yet

    public LiveData<String> logout() {
        MutableLiveData<String> message = new MutableLiveData<>();

        apiService.logout().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            JSONObject object = new JSONObject(new Gson().toJson(response.body()));
                            String msg = object.getString("message");
                            Log.d(TAG, "onResponse: " + msg);
                            message.postValue(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
        return message;
    }

}
