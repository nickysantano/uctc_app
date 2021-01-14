package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.response.role.ActionPlanResponse;
import com.example.uctc_app.model.response.role.TaskResponse;
import com.example.uctc_app.network.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {
    private static TaskRepository taskRepository;
    private RetrofitService apiService;
    private static final String TAG = "TaskRepository";

    private TaskRepository(String token) {
        Log.d(TAG, "taskRepository: " + token);
        apiService = RetrofitService.getInstance(token);
    }

    public static TaskRepository getInstance(String token) {
        if (taskRepository == null) {
            taskRepository = new TaskRepository(token);
        }
        return taskRepository;
    }

    public synchronized void resetInstance() {
        if(taskRepository != null) {
            taskRepository = null;
        }
    }

    public MutableLiveData<List<Task>> getTasks(int id) {
        MutableLiveData<List<Task>> listTasks = new MutableLiveData<>();

        apiService.getTasks(id).enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Program " + response.body().getResults().size());
                        listTasks.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTasks;
    }

    public MutableLiveData<List<Task>> getMyTasks(int id) {
        MutableLiveData<List<Task>> listTasks = new MutableLiveData<>();

        apiService.getMyTasks(id).enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Program " + response.body().getResults().size());
                        listTasks.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTasks;
    }
}
