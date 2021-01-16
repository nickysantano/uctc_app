package com.example.uctc_app.ui.pages.user.my_program;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.action_plan.DetailToDoListStaffFragmentArgs;
import com.example.uctc_app.ui.pages.staff.action_plan.TaskStaffViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailToDoListFragment extends Fragment {

    @BindView(R.id.lbl_to_do_list_title)
    TextView titleTask;

    @BindView(R.id.lbl_to_do_list_description)
    TextView titleDescription;

    @BindView(R.id.lbl_pic_task)
    TextView picTask;

    @BindView(R.id.lbl_due_date_task)
    TextView dateTask;

    private TaskStaffViewModel viewModel;
    private SharedPreferenceHelper helper;
    Task task;

    public DetailToDoListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_to_do_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TaskStaffViewModel.class);
        viewModel.init(helper.getAccessToken());

        if (getArguments() != null){
            task = DetailToDoListStaffFragmentArgs.fromBundle(getArguments()).getDetailTask();
            initDetailTask(task);
        }

    }

//    @OnClick({R.id.btn_detail_program_action_plan_program})
//    public void onClick(View view) {
//        DetailProgramStaffFragmentDirections.ActionDetailProgramStaffToActionPlan actionDetailProgramStaffToActionPlan =
//                DetailProgramStaffFragmentDirections.actionDetailProgramStaffToActionPlan(program.getProgram_id() + "");
//        Navigation.findNavController(view).navigate(actionDetailProgramStaffToActionPlan);
//    }

    private void initDetailTask(Task task) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle("Detail Program");
        titleTask.setText(task.getName());
        titleDescription.setText(task.getDescription());
//        picTask.setText(task.getPic());
        dateTask.setText(task.getDate());
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }
}