package com.example.uctc_app.repository.login;

import android.media.session.MediaSession;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private static AuthRepository authRepository;
    private RetrofitService apiService;
    private static final String TAG = "AuthRepository";

    private AuthRepository() {
        apiService = RetrofitService.getInstance("");
    }

    public static AuthRepository getInstance() {
        if (authRepository == null) {
            authRepository = new AuthRepository();
        }
        return authRepository;
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

}
