package com.example.uctc_app.ui.pages.user.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentDirections;
import com.example.uctc_app.utils.Constants;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileUserFragment extends Fragment {

    @BindView(R.id.btn_logout)
    Button btn_logout;

    private ProfileUserViewModel viewModel;
    private SharedPreferenceHelper helper;

    public ProfileUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        viewModel.getUser().observe(requireActivity(), observer);

//        if (getArguments() != null) {
//            initUser(user);
//        }else
//            Log.d("HAI ERROR = ", "Profile ini null");
//        }
    }

    @OnClick(R.id.btn_logout)
    public void logout(View view) {
        if (view.getId() == R.id.btn_logout) {
            viewModel.logout().observe(requireActivity(), message -> {
                if (!message.isEmpty()) {
                    helper.clearPref();
                    NavDirections action = ProfileUserFragmentDirections.actionNavProfileToLoginFragment();
                    Navigation.findNavController(view).navigate(action);
                    Toast.makeText(ProfileUserFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick({R.id.btn_faqs})
    public void onClick(View view) {
        NavDirections action = ProfileUserFragmentDirections.actionProfileToManual();
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }

    @BindView(R.id.imgProfile_user)
    ImageView imgProfile;

    @BindView(R.id.lbl_name_profile)
    TextView name;

    @BindView(R.id.lbl_roles_profile)
    TextView role;

    @BindView(R.id.lbl_email_profile)
    TextView email;

    @BindView(R.id.lbl_department_profile)
    TextView department;

    @BindView(R.id.lbl_phone_profile)
    TextView phone;

    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            Glide.with(getActivity()).load(Constants.BASE_IMAGE_URL + user.getPicture()).into(imgProfile);
            name.setText(user.getName());
            if (user.getRole_id().equalsIgnoreCase("2")){
                role.setText("LECTURER");
            }else {
                role.setText("STUDENT");
            }
//            role.setText(user.getRole_id());
            email.setText(user.getEmail());
            department.setText(user.getDepartment_id());
            phone.setText(user.getPhone_number());
        }

    };

}