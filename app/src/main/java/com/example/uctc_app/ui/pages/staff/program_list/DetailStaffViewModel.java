package com.example.uctc_app.ui.pages.staff.program_list;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.uctc_app.repository.login.ProgramRepository;

public class DetailStaffViewModel extends ViewModel {

    private ProgramRepository repository;
    private static final String TAG = "ProfileViewModel";

    public DetailStaffViewModel() {

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
