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
import android.widget.EditText;
import android.widget.Spinner;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.repository.login.TaskRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateTaskStaffFragment extends Fragment {

    @BindView(R.id.lbl_input_name_task)
    TextInputLayout lbl_taskName;

    @BindView(R.id.lbl_input_description_task)
    EditText lbl_taskDescription;

    @BindView(R.id.lbl_input_date_task)
    TextInputLayout lbl_taskDate;

    @BindView(R.id.spinner_pic_add_task)
    Spinner spinner_pic;

    @BindView(R.id.button_updateTask)
    Button btnUpdateTask;

//    TextInputLayout lbl_taskName, lbl_taskDate;
//    TextInputEditText lbl_taskDescription;
//    Spinner spinner_pic;

    SharedPreferenceHelper helper;
    ProgramRepository programRepository;
    Adapter adapter;
    TaskRepository taskRepository;
    Context context;
    ArrayList<String> namaTeam = new ArrayList<>();
    ArrayList<Integer> namaTeamId = new ArrayList<>();
    int setPICSpinnerPos;

    private int actionPlan_id;
    private String program_id;
    private Task task;

    public UpdateTaskStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_task_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().hide();

        actionPlan_id = UpdateTaskStaffFragmentArgs.fromBundle(getArguments()).getActionPlanId();
        program_id = UpdateTaskStaffFragmentArgs.fromBundle(getArguments()).getProgramId();
        task = UpdateTaskStaffFragmentArgs.fromBundle(getArguments()).getDetailTask();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        context = getActivity();
        programRepository = ProgramRepository.getInstance(helper.getAccessToken());
        programRepository.getCommittees(Integer.parseInt(program_id)).observe(requireActivity(), observer);

        lbl_taskName.getEditText().setText(task.getName());
        lbl_taskDescription.setText(task.getDescription());
        lbl_taskDate.getEditText().setText(task.getDate());
//        namaTeamId.get(spinner_pic.getSelectedItemPosition());

        btnUpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Spinner PIC: ",spinner_pic.getSelectedItem().toString());
                taskRepository = TaskRepository.getInstance(helper.getAccessToken());
                taskRepository.updateTask(task.getTask_id(), new Task( lbl_taskName.getEditText().getText().toString(), task.getStatus(),
                        lbl_taskDescription.getText().toString(), lbl_taskDate.getEditText().getText().toString(), actionPlan_id, namaTeamId.get(spinner_pic.getSelectedItemPosition())));

                UpdateTaskStaffFragmentDirections.ActionUpdateTaskStaffToToDoList actionUpdateTaskToTaskStaff =
                        UpdateTaskStaffFragmentDirections.actionUpdateTaskStaffToToDoList(actionPlan_id, program_id);
                Navigation.findNavController(v).navigate(actionUpdateTaskToTaskStaff);
            }
        });
    }

    private Observer<List<User>> observer = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            namaTeam.clear();
            namaTeamId.clear();

            if (users!=null){
                User[] teamArr = new User[users.size()];
                teamArr = users.toArray(teamArr);

                for (int i = 0; teamArr.length > i; i++){
                    namaTeam.add(teamArr[i].getName());
                    namaTeamId.add(teamArr[i].getUser_id());
                    if (task.getPic() == teamArr[i].getUser_id()){
                        setPICSpinnerPos = i;
                    }
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, namaTeam);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_pic.setAdapter(dataAdapter);
                spinner_pic.setSelection(setPICSpinnerPos);
            }
        }
    };
}