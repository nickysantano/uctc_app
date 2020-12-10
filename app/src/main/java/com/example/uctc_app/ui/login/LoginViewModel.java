package com.example.uctc_app.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.uctc_app.model.response.role.TokenResponse;
import com.example.uctc_app.repository.login.AuthRepository;

public class LoginViewModel extends ViewModel {

    private AuthRepository repository;

    public LoginViewModel() {
        repository = AuthRepository.getInstance();
    }

    public MutableLiveData<TokenResponse> login(String email, String password) {
        return  repository.login(email,password);
    }

}
