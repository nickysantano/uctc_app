package com.example.uctc_app.ui.pages.user.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.uctc_app.R;
import com.example.uctc_app.ui.login.LoginFragmentDirections;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileUserFragment extends Fragment {

    @BindView(R.id.btn_login)
    Button btn_login;

    public ProfileUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @OnClick({R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:

        }
    }
}