package com.example.uctc_app.ui.pages.user.my_program;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ActionPlanRepository;
import com.example.uctc_app.repository.login.ProgramRepository;

import java.util.List;

public class ActionPlanViewModel extends ViewModel {

    private ActionPlanRepository repository;
    private static final String TAG = "ActionPlanViewModel";

    public ActionPlanViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = ActionPlanRepository.getInstance(token);
    }

    public LiveData<List<ActionPlan>> getActionPlans(int id) {
        Log.d("Hello","VIewModel");
        return repository.getActionPlans(id);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
