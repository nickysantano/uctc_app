package com.example.uctc_app.ui.pages.staff.action_plan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ActionPlanRepository;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActionPlanStaffFragment extends Fragment {
    @BindView(R.id.lbl_action_plan_name)
    TextInputLayout actionPlanName;

    @BindView(R.id.lbl_action_plan_description)
    EditText actionPlanDescription;

    @BindView(R.id.btn_add_action_plan)
    Button btnUpdateActionPlan;

    SharedPreferenceHelper helper;
    ProgramRepository programRepository;
    ActionPlanRepository actionPlanRepository;
    Context context;
    ActionPlan actionPlan;

    private String program_id;

    public UpdateActionPlanStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_action_plan_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        program_id = UpdateActionPlanStaffFragmentArgs.fromBundle(getArguments()).getProgramId();
        actionPlan = UpdateActionPlanStaffFragmentArgs.fromBundle(getArguments()).getThisActionPlan();
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        context = getActivity();

        actionPlanName.getEditText().setText(actionPlan.getName());
        actionPlanDescription.setText(actionPlan.getDescription());

        btnUpdateActionPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActionPlanRepository actionPlanRepository = ActionPlanRepository.getInstance(helper.getAccessToken());
                actionPlanRepository.updateActionPlan(actionPlan.getId(), new ActionPlan(actionPlanName.getEditText().getText().toString(),
                        actionPlanDescription.getText().toString(), Integer.parseInt(program_id)));

                UpdateActionPlanStaffFragmentDirections.ActionUpdateActionPlanToActionPlanStaff actionUpdateActionPlanToActionPlanStaff =
                        UpdateActionPlanStaffFragmentDirections.actionUpdateActionPlanToActionPlanStaff(program_id);
                Navigation.findNavController(v).navigate(actionUpdateActionPlanToActionPlanStaff);
            }
        });
    }

//    @OnClick({R.id.btn_add_task})
//    public void onClick(View view) {
//        NavDirections action = AddTaskStaffFragmentDirections.actionAddStaffFragmentToToDoList();
//        Navigation.findNavController(view).navigate(action);
//    }
}