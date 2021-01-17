package com.example.uctc_app.ui.pages.staff.profile;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserFragment;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileStaffFragment extends Fragment {

    @BindView(R.id.btn_logout)
    Button btn_logout;

    private ProfileUserViewModel viewModel;
    private SharedPreferenceHelper helper;
//    private Context context;
//    private List<User> userData;
//    private User user;

    public ProfileStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ProfileUserViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getUser().observe(requireActivity(), observer);
    }

    @OnClick({R.id.btn_faqs_staff})
    public void onClick(View view) {
        NavDirections action = ProfileStaffFragmentDirections.actionProfileStaffToManual();
        Navigation.findNavController(view).navigate(action);
    }

    @OnClick(R.id.btn_logout)
    public void logout(View view) {
        if (view.getId() == R.id.btn_logout) {
            viewModel.logout().observe(requireActivity(), new Observer<String>() {
                @Override
                public void onChanged(String message) {
                    if (!message.isEmpty()) {
                        helper.clearPref();
                        NavDirections action = ProfileStaffFragmentDirections.actionProfileStaffToLogin();
                        Navigation.findNavController(view).navigate(action);
                        Toast.makeText(ProfileStaffFragment.this.getActivity(), message, Toast.LENGTH_SHORT).show();
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
            name.setText(user.getName());
            role.setText(user.getRole_id());
            email.setText(user.getEmail());
            department.setText(user.getDepartment_id());
            phone.setText(user.getPhone_number());
        }

//        @Override
//        public void onChanged(List<User> users) {
//            if (users != null){
//                User user = users.get(0);
//                name.setText(user.getName());
//                role.setText(user.getRole_id());
//                email.setText(user.getEmail());
//                department.setText(user.getDepartment_id());
//                phone.setText(user.getPhone_number());
//            }
//        }
    };

}