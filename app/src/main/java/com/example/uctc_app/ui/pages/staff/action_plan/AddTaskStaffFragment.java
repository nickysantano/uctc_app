package com.example.uctc_app.ui.pages.staff.action_plan;

import android.content.Context;
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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTaskStaffFragment extends Fragment {

    @BindView(R.id.lbl_input_name_task)
    TextInputLayout lbl_taskName;

    @BindView(R.id.lbl_input_description_task)
    EditText lbl_taskDescription;

    @BindView(R.id.lbl_input_date_task)
    TextInputLayout lbl_taskDate;

    @BindView(R.id.spinner_pic_add_task)
    Spinner spinner_pic;

    @BindView(R.id.button_addTask)
    Button btnAddTask;

    SharedPreferenceHelper helper;
    ProgramRepository programRepository;
    Adapter adapter;
    TaskRepository taskRepository;
    Context context;
    ArrayList<String> namaTeam = new ArrayList<>();
    ArrayList<Integer> namaTeamId = new ArrayList<>();

    private int actionPlan_id;
    private String program_id;

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

        actionPlan_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getActionPlanId();
        program_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        context = getActivity();
        programRepository = ProgramRepository.getInstance(helper.getAccessToken());
        programRepository.getCommittees(Integer.parseInt(program_id)).observe(requireActivity(), observer);

        //Need from navigation, program Id and Action Plan ID

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Spinner PIC: ",spinner_pic.getSelectedItem().toString());
                taskRepository = TaskRepository.getInstance(helper.getAccessToken());
                taskRepository.addTask(new Task( lbl_taskName.getEditText().getText().toString(), "0",
                        lbl_taskDescription.getText().toString(), lbl_taskDate.getEditText().getText().toString(), actionPlan_id, namaTeamId.get(spinner_pic.getSelectedItemPosition())));

                AddTaskStaffFragmentDirections.ActionAddStaffFragmentToToDoList actionAddStaffFragmentToToDoList = AddTaskStaffFragmentDirections.actionAddStaffFragmentToToDoList(actionPlan_id, program_id);
                Navigation.findNavController(v).navigate(actionAddStaffFragmentToToDoList);
            }
        });
    }

    private Observer <List<User>> observer = new Observer<List<User>>() {
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
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, namaTeam);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_pic.setAdapter(dataAdapter);
                //need to check from previous UTS to how to fill in

            }
        }
    };


//    @OnClick({R.id.btn_add_task})
//    public void onClick(View view) {
//        NavDirections action = AddTaskStaffFragmentDirections.actionAddStaffFragmentToToDoList();
//        Navigation.findNavController(view).navigate(action);
//    }
}