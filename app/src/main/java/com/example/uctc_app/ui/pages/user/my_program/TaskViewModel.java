package com.example.uctc_app.ui.pages.user.my_program;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.repository.login.TaskRepository;

import java.util.List;

public class TaskViewModel extends ViewModel {

    private TaskRepository repository;
    private static final String TAG = "TaskViewModel";

    public TaskViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = TaskRepository.getInstance(token);
    }

    public MutableLiveData<List<Task>> getTask(int id) {
        Log.d("getTask: ", "Success");
        return repository.getTasks(id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
