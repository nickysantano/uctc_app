package com.example.uctc_app.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.R;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentArgs;
import com.example.uctc_app.ui.splash.SplashFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {

    @BindView(R.id.email_text)
    TextInputEditText email_text;
    @BindView(R.id.password_text)
    TextInputEditText password_text;
    @BindView(R.id.btn_login)
    Button btn_login;

    LoginViewModel viewModel;
    SharedPreferenceHelper helper;
    private ProfileRepository repository;
    public View currentView;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (!email_text.getText().toString().isEmpty() && !password_text.getText().toString().isEmpty()) {
                    String email = email_text.getText().toString().trim();
                    String password = password_text.getText().toString().trim();
                    viewModel.login(email, password).observe(requireActivity(), tokenResponse -> {
                        if (tokenResponse != null) {
                            helper.saveAccessToken(tokenResponse.getAuthorization());
                            currentView = view;
                            if (currentView!=null){
                                repository = ProfileRepository.getInstance(helper.getAccessToken());
                                repository.getUser().observe(requireActivity(), observer );
                            }

                        }else {
                            Toast.makeText(requireActivity(), "Error login. Check your email and password.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(requireActivity(), "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
                Log.d("USER ROLE", "hai rayyyyyyyyyyyyyyyyyyyyy");
            NavDirections action;
            if (user.getRole_id().equalsIgnoreCase("1")) {
                Log.d("USER ROLE", "ADMIIIN");
                action = LoginFragmentDirections.actionLoginToHomeAdmin();
            } else if (user.getRole_id().equalsIgnoreCase("2")) {
                Log.d("USER ROLE", "STAFF");
                action = LoginFragmentDirections.actionLoginToHomeStaffNavigation();
            } else {
                Log.d("USER ROLE", "USER");
                action = LoginFragmentDirections.actionLoginToHomeUser();
            }
            Navigation.findNavController(currentView).navigate(action);
            Toast.makeText(requireActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();


        }
    };
}