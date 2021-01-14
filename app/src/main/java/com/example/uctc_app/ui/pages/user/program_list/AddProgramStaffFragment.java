package com.example.uctc_app.ui.pages.user.program_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.model.response.role.ProgramResponse;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.login.LoginFragmentDirections;
import com.example.uctc_app.ui.login.LoginViewModel;
import com.example.uctc_app.ui.pages.user.home.HomeUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.home.RecentEventAdapter;
import com.example.uctc_app.ui.splash.SplashFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Retrofit;

public class AddProgramStaffFragment extends Fragment {
    @BindView(R.id.textInputLayout10)
    TextInputLayout lbl_add_program_name_staff;

    @BindView(R.id.textInputLayout5)
    TextInputLayout lbl_add_description_program_staff;

    @BindView(R.id.textInputLayout7)
    TextInputLayout lbl_add_goal_program_staff;

    @BindView(R.id.textInputLayout9)
    TextInputLayout lbl_add_client_staff;

//    @BindView(R.id.editTextDate)
//    TextInputEditText editTextDate;

    @BindView(R.id.btn_add_program_staff)
    Button btn_add_program_staff;

    ProgramViewModel viewModel;
    SharedPreferenceHelper helper;
    ProfileRepository repositoryProfile;
    ProgramRepository repositoryProgram;
    public AddProgramStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_program_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        viewModel = ViewModelProviders.of(requireActivity()).get(ProgramViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        btn_add_program_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repositoryProfile = ProfileRepository.getInstance(helper.getAccessToken());
                repositoryProfile.getUser().observe(requireActivity(), observer );
            }
        });
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecentEventAdapter.ViewHolder holder, int position) {
//        holder.itemView.setOnClickListener(view -> {
//            HomeUserFragmentDirections.ActionHomeUserToDetailProgramUser actionHomeUserToDetailProgramUser = HomeUserFragmentDirections.actionHomeUserToDetailProgramUser(program);
//            Navigation.findNavController(view).navigate(actionHomeUserToDetailProgramUser);
//        });
//    }

    @OnClick({R.id.btn_add_program_staff})
    public void onClick(View view) {
        repositoryProfile = ProfileRepository.getInstance(helper.getAccessToken());
        repositoryProfile.getUser().observe(requireActivity(), observer );

    }
    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
        if (user != null){
            Log.d("CHECK", "ADDING PROGRAM");
            repositoryProgram= ProgramRepository.getInstance(helper.getAccessToken());
            repositoryProgram.addProgram(new Program(lbl_add_program_name_staff.getEditText().getText().toString(),
                    lbl_add_description_program_staff.getEditText().getText().toString(),lbl_add_goal_program_staff.getEditText().getText().toString(), user.getUser_id() + "","Started", "2000-10-10"));

        }
             }
    };
//    public void saveProgram(Program program){
//        Call<ProgramResponse> programResponseCall =
//    }


}