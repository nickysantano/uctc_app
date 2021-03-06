package com.example.uctc_app.ui.pages.staff.action_plan;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.repository.login.TaskRepository;

import java.util.List;

public class TaskStaffViewModel extends ViewModel {

    private TaskRepository repository;
    private static final String TAG = "TaskViewModel";

    public TaskStaffViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = TaskRepository.getInstance(token);
    }

    public MutableLiveData<List<Task>> getTask(int id) {
        Log.d("getTask: ", "Success");
        return repository.getTasks(id);
    }

    public MutableLiveData<List<Task>> getMyTask(int id) {
        Log.d("getMyTask: ", "Success");
        return repository.getMyTasks(id);
    }

    public void deleteTask(int id) {
        Log.d("getMyTask: ", "Success");
        repository.deleteTask(id);
    }

    public void updateTask(int id,Task task){
        repository.updateTask(id,task);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
