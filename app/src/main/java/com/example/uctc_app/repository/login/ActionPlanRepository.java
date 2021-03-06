package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.response.role.ActionPlanResponse;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionPlanRepository {
    private static ActionPlanRepository actionPlanRepository;
    private RetrofitService apiService;
    private static final String TAG = "ProgramRepository";

    private ActionPlanRepository(String token) {
        Log.d(TAG, "actionPlanRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static ActionPlanRepository getInstance(String token) {
        if (actionPlanRepository == null) {
            actionPlanRepository = new ActionPlanRepository(token);
        }
        return actionPlanRepository;
    }

    public synchronized void resetInstance() {
        if(actionPlanRepository != null) {
            actionPlanRepository = null;
        }
    }

    public MutableLiveData<List<ActionPlan>> getActionPlans(int id) {
        MutableLiveData<List<ActionPlan>> listAction = new MutableLiveData<>();

        apiService.getActionPlans(id).enqueue(new Callback<ActionPlanResponse>() {
            @Override
            public void onResponse(Call<ActionPlanResponse> call, Response<ActionPlanResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Action Plan " + response.body().getResults().size());
                        listAction.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<ActionPlanResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listAction;
    }

    public void addActionPlan(ActionPlan actionPlan){
        apiService.addActionPlan(actionPlan).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "onResponse:" + response.message());
                Log.d("WIFI SUCCESS", "ADDING ActionPlan, name: " + actionPlan.getName());
                Log.d("WIFI SUCCESS", "ADDING ActionPlan, desc: " + actionPlan.getDescription());
                Log.d("WIFI SUCCESS", "ADDING ActionPlan, program: " + actionPlan.getProgram());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    public  void deleteActionPlan(int id){
        apiService.deleteActionPlan(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "DELETE Action Plan plz i need my sleep:" + response.message());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    public void updateActionPlan(int id, ActionPlan actionPlan){
        apiService.updateActionPlan(id,actionPlan).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "onResponse:" + response.message());
                Log.d(TAG, "onResponse Task:" + response.body());
                Log.d("WIFI SUCCESS", "UPDATING Task");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
