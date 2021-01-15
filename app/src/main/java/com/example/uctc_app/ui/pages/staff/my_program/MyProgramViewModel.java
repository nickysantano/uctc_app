package com.example.uctc_app.ui.pages.staff.my_program;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;

import java.util.List;

public class MyProgramViewModel extends ViewModel {

    private ProgramRepository repository;
    private static final String TAG = "MyProgramViewModel";

    public MyProgramViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = ProgramRepository.getInstance(token);
    }

    public LiveData<List<Program>> myPrograms(String id) {
        Log.d("Hello","ViewModel");
        return repository.myPrograms(id);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
