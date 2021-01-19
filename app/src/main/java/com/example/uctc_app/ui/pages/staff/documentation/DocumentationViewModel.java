package com.example.uctc_app.ui.pages.staff.documentation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.local.role.Documentation;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;

import java.util.List;

public class DocumentationViewModel extends ViewModel {

    private ProgramRepository repository;
    private static final String TAG = "DocumentationViewModel";

    public DocumentationViewModel() {

    }

    public void init(String token) {
        Log.d(TAG, "init: " + token);
        repository = ProgramRepository.getInstance(token);
    }

    public LiveData<List<Documentation>> documentation (int id) {
        Log.d("Hello","ViewModel");
        return repository.getDocumentation(id);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
        repository.resetInstance();
    }
}
