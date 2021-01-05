package com.example.uctc_app.ui.pages.user.program_list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;

import java.util.List;

public class ProgramViewModel extends ViewModel {

    private ProgramRepository repository;
    private static final String TAG = "ProgramViewModel";

    public ProgramViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = ProgramRepository.getInstance(token);
    }

    public LiveData<List<Program>> getPrograms() {
        Log.d("Hello","VIewModel");
        return repository.getPrograms();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
