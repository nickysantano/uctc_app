package com.example.uctc_app.ui.splash;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.Objects;

import butterknife.ButterKnife;

public class SplashFragment extends Fragment {

    private SharedPreferenceHelper helper;

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(requireActivity());
        ButterKnife.bind(this, view);

//        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action;
            if(helper.getAccessToken().isEmpty()){
                action = SplashFragmentDirections.actionSplashToLogin();
            }else {
                action = SplashFragmentDirections.actionSplashToHomeUser();
            }
            Navigation.findNavController(view).navigate(action);
        }, 1500);
    }

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