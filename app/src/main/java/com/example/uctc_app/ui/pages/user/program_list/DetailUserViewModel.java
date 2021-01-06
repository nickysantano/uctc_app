package com.example.uctc_app.ui.pages.user.program_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.repository.login.ProgramRepository;

import java.util.List;

public class DetailUserViewModel extends ViewModel {

    private ProgramRepository repository;
    private static final String TAG = "ProfileViewModel";

    public DetailUserViewModel() {

    }

    public void init (String token) {
        Log.d(TAG, "init: " + token);
        repository = ProgramRepository.getInstance(token);
    }

    @Override
    public void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
