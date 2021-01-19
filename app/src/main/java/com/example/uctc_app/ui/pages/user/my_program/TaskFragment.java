package com.example.uctc_app.ui.pages.user.my_program;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.action_plan.TaskStaffAdapter;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentArgs;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskFragment extends Fragment {

    @BindView(R.id.rv_task_list)
    RecyclerView rvTask;

    @BindView(R.id.progressBar_cover)
    ImageView loading_cover;

    @BindView(R.id.progressBar)
    LottieAnimationView loading;

    @BindView(R.id.lbl_no_data)
    TextView noDataText;

    @BindView(R.id.btn_add_task)
    FloatingActionButton addTask;

    private TaskViewModel viewModel;
    private TaskUserAdapter adapter;
    private SharedPreferenceHelper helper;
    private int actionPlan_id;
    private String program_id;

    public TaskFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_to_do_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        checkNoData(true);

        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        actionPlan_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getActionPlanId();
        program_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TaskViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getTask(TaskFragmentArgs.fromBundle(getArguments()).getActionPlanId()).observe(requireActivity(), observeViewModel);

        rvTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskUserAdapter(getActivity());
    }

    @OnClick({R.id.btn_add_task})
    public void onClick(View view) {
        NavDirections action = TaskFragmentDirections.actionTaskToAddTaskUser(actionPlan_id, program_id);
        Navigation.findNavController(view).navigate(action);
    }

//    @OnClick({R.id.btnPic})
//    public void onClick(View view) {
//        //
//    }

    private Observer<List<Task>> observeViewModel = new Observer<List<Task>>() {
        @Override
        public void onChanged(List<Task> tasks) {
            if (tasks != null) {
                adapter.setTaskList(tasks, actionPlan_id, program_id);
                adapter.notifyDataSetChanged();
                rvTask.setAdapter(adapter);
                showLoading(false);
                checkNoData(false);
            }else if(tasks == null){
                checkNoData(true);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvTask.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            loading_cover.setVisibility(View.VISIBLE);
        } else {
            rvTask.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            loading_cover.setVisibility(View.GONE);
        }
    }

    private void checkNoData(Boolean state) {
        if (state) {
            rvTask.setVisibility(View.GONE);
            noDataText.setVisibility(View.VISIBLE);
        } else {
            rvTask.setVisibility(View.VISIBLE);
            noDataText.setVisibility(View.GONE);
        }
    }
}