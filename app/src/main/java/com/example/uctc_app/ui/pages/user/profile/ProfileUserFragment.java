package com.example.uctc_app.ui.pages.user.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.uctc_app.MainActivity;
import com.example.uctc_app.R;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileUserFragment extends Fragment {

    @BindView(R.id.btn_logout)
    Button btn_logout;

    private ProfileUserViewModel viewModel;
    private SharedPreferenceHelper helper;

    public ProfileUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).hide();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ProfileUserViewModel.class);
        viewModel.init(helper.getAccessToken());


    }

    @OnClick(R.id.btn_logout)
    public void logout(View view) {
        if (view.getId() == R.id.btn_logout) {
            viewModel.logout().observe(requireActivity(), new Observer<String>() {
                @Override
                public void onChanged(String message) {
                    if (!message.isEmpty()) {
                        helper.clearPref();
                        NavDirections action = ProfileUserFragmentDirections.actionNavProfileToLoginFragment();
                        Navigation.findNavController(view).navigate(action);
                        Toast.makeText(ProfileUserFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}