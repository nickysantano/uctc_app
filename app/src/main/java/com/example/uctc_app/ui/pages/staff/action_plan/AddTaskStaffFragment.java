package com.example.uctc_app.ui.pages.staff.action_plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.repository.login.TaskRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentArgs;
import com.example.uctc_app.ui.pages.user.program_list.AddProgramStaffFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskStaffFragment extends Fragment {

    @BindView(R.id.lbl_input_name_task)
    TextInputLayout lbl_taskName;

    @BindView(R.id.lbl_input_description_task)
    TextInputLayout lbl_taskDescription;

    @BindView(R.id.lbl_input_year_task)
    TextInputLayout lbl_taskYear;

    @BindView(R.id.lbl_input_month_task)
    TextInputLayout lbl_taskMonth;

    @BindView(R.id.lbl_input_day_task)
    TextInputLayout lbl_taskDay;

    @BindView(R.id.spinner_pic_add_task)
    Spinner spinner_pic;

    @BindView(R.id.button_addTask)
    Button button_addtask;

    SharedPreferenceHelper helper;
    ProfileRepository repositoryProfile;

    TaskRepository taskRepository;

    public AddTaskStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_add_task_staff, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        button_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repositoryProfile = ProfileRepository.getInstance(helper.getAccessToken());
                repositoryProfile.getUser().observe(requireActivity(), observer );



            }
        });
    }

    private Observer<User> observer = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            if (user != null){
                Log.d("CHECK", "ADDING PROGRAM");
                taskRepository = TaskRepository.getInstance(helper.getAccessToken());
                taskRepository.addTask(new Task( lbl_taskName.getEditText().getText().toString(), "0", lbl_taskDescription.getEditText().getText().toString(),
                        lbl_taskYear.getEditText().getText().toString()  + "-" + lbl_taskMonth.getEditText().getText().toString() + "-"+lbl_taskDay.getEditText().getText().toString(),
                        Integer.parseInt(?????????????.fromBundle(getArguments()).getId()), user.getUser_id()));
                AddProgramStaffFragmentDirections.ActionAddProgramToProgramUser actionAddProgramToProgramUser = AddProgramStaffFragmentDirections.actionAddProgramToProgramUser();
                Navigation.findNavController(v).navigate(actionAddProgramToProgramUser);

            }
        }
    };



//    @OnClick({R.id.btn_add_task})
//    public void onClick(View view) {
//        NavDirections action = AddTaskStaffFragmentDirections.actionAddStaffFragmentToToDoList();
//        Navigation.findNavController(view).navigate(action);
//    }
}