package com.example.uctc_app.ui.pages.staff.action_plan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ActionPlanRepository;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActionPlanStaffFragment extends Fragment {

    @BindView(R.id.lbl_action_plan_name)
    Spinner actionPlanName;

    @BindView(R.id.lbl_action_plan_description)
    Button actionPlanDescription;

    @BindView(R.id.btn_add_action_plan)
    Button btnAddActionPlan;

    SharedPreferenceHelper helper;
    ProgramRepository programRepository;
    ActionPlanRepository actionPlanRepository;
    Context context;

    private String program_id;

    public AddActionPlanStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_action_plan_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        program_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        context = getActivity();
        programRepository = ProgramRepository.getInstance(helper.getAccessToken());
        programRepository.getCommittees(Integer.parseInt(program_id)).observe(requireActivity(), observer);

        btnAddActionPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActionPlanRepository actionPlanRepository = ActionPlanRepository.getInstance(helper.getAccessToken());
//                actionPlanRepository.addTask(new Task(actionPlanName.getEditText().getText().toString(), actionPlanDescription.getText().toString()));

                AddActionPlanStaffFragmentDirections.ActionAddActionPlanStaffToActionPlan actionAddActionPlanStaffToActionPlan =
                        AddActionPlanStaffFragmentDirections.actionAddActionPlanStaffToActionPlan(program_id);
                Navigation.findNavController(v).navigate(actionAddActionPlanStaffToActionPlan);
            }
        });
    }

    private Observer<List<User>> observer = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {

            }
    };


//    @OnClick({R.id.btn_add_task})
//    public void onClick(View view) {
//        NavDirections action = AddTaskStaffFragmentDirections.actionAddStaffFragmentToToDoList();
//        Navigation.findNavController(view).navigate(action);
//    }
}