package com.example.uctc_app.ui.pages.staff.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;

public class ProfileStaffViewModel extends ViewModel {

    private ProfileRepository repository;
    private static final String TAG = "ProfileViewModel";

    public ProfileStaffViewModel() {

    }

    public void init (String token) {
        Log.d(TAG, "init: " + token);
        repository = ProfileRepository.getInstance(token);
    }

    public MutableLiveData<User> getUser() {
        Log.d("Hello","VIewModel");
        return repository.getUser();
    }

    public LiveData<String> logout() {
        return repository.logout();
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}