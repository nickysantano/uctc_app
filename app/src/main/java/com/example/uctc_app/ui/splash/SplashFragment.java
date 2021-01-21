package com.example.uctc_app.ui.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import butterknife.ButterKnife;

public class SplashFragment extends Fragment {

    private ProfileRepository repository;
    public View currentView;
    private BottomNavigationView navigationViewAdmin, navigationViewStaff, navigationViewUser;

    public SplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(requireActivity());
        ButterKnife.bind(this, view);
        currentView = view;
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action;
            if (currentView !=null){
                if(helper.getAccessToken().isEmpty()){
                    action = SplashFragmentDirections.actionSplashToLogin();
                    Navigation.findNavController(currentView).navigate(action);
                }else {
                    repository = ProfileRepository.getInstance(helper.getAccessToken());
                    repository.getUser().observe(requireActivity(), observer );
                    Log.d("Checking ROLE", "IN PROCESS");
                }
            }

        }, 1500);
    }
    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            NavDirections action;
            if (user.getRole_id().equalsIgnoreCase("1")){
                Log.d("USER ROLE", "ADMIIIN");
                action = SplashFragmentDirections.actionSplashToAdmin();
            }
            else if (user.getRole_id().equalsIgnoreCase("2")){
                Log.d("USER ROLE", "STAFF");
                action = SplashFragmentDirections.actionSplashToStaff();
            }
            else{
                Log.d("USER ROLE", "USER");
                action = SplashFragmentDirections.actionSplashToHomeUser();
            }
            Navigation.findNavController(currentView).navigate(action);

        }

    };
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity)getActivity()).getSupportActionBar().hide();
    }
}