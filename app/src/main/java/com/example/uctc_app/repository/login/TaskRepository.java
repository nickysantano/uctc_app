package com.example.uctc_app.repository.login;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.response.role.TaskResponse;
import com.example.uctc_app.model.response.role.TasksResponse;
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

        apiService.getTasks(id).enqueue(new Callback<TasksResponse>() {
            @Override
            public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                Log.d(TAG, "onResponse: haiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Task " + response.body().getResults().size());
                        listTasks.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TasksResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTasks;
    }

    public MutableLiveData<List<Task>> getMyTasks(int id) {
        MutableLiveData<List<Task>> listTasks = new MutableLiveData<>();

        apiService.getMyTasks(id).enqueue(new Callback<TasksResponse>() {
            @Override
            public void onResponse(Call<TasksResponse> call, Response<TasksResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: Program " + response.body().getResults().size());
                        listTasks.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TasksResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return listTasks;
    }

    public void addTask(Task task){
        apiService.addTask(task).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "onResponse:" + response.message());
                Log.d("WIFI SUCCESS", "ADDING Task");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    public  void deleteTask(int id){
        apiService.deleteTask(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    public void updateTask(int id, Task task){
        apiService.updateTask(id,task).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse:" + response.code());
                Log.d(TAG, "onResponse:" + response.message());
                Log.d(TAG,"onResponseBody : " + response.body());
                Log.d("WIFI SUCCESS", "UPDATING Task");
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public MutableLiveData<Task> getTask(int id) {
        MutableLiveData<Task> task = new MutableLiveData<>();

        apiService.getTask(id).enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                Log.d(TAG, "onResponse: " + response.code());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: User " + response.body().getResults());
                        task.postValue(response.body().getResults());
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

        return task;
    }


}
