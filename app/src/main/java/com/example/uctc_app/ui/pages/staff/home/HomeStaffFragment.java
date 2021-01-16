package com.example.uctc_app.ui.pages.staff.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.action_plan.TaskStaffAdapter;
import com.example.uctc_app.ui.pages.staff.action_plan.ToDoListStaffFragmentArgs;
import com.example.uctc_app.ui.pages.user.home.RecentEventAdapter;
import com.example.uctc_app.ui.pages.user.home.TaskHomeAdapter;
import com.example.uctc_app.ui.pages.user.home.TaskHomeViewModel;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeStaffFragment extends Fragment {

    @BindView(R.id.rv_to_do_list_staff)
    RecyclerView rvTask;

    private TaskStaffAdapter adapterTask;
    private ProfileUserViewModel viewModelProfile;
    private TaskHomeViewModel viewModelTask;
    private SharedPreferenceHelper helper;
    private int actionPlan_id;
    private String program_id;

    public HomeStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

//        showLoading(true);
        Log.d("Hello","In the java");
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

//        actionPlan_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getActionPlanId();
//        program_id = ToDoListStaffFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());                                 //buat isa dapet idnya profile
        viewModelProfile = ViewModelProviders.of(requireActivity()).get(ProfileUserViewModel.class);
        viewModelProfile.init(helper.getAccessToken());
        viewModelProfile.getUser().observe(requireActivity(), observeViewModelProfile);

        viewModelTask = ViewModelProviders.of(requireActivity()).get(TaskHomeViewModel.class);
        viewModelTask.init(helper.getAccessToken());
        rvTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterTask = new TaskStaffAdapter(getActivity());
    }

    private Observer<User> observeViewModelProfile = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            if (user != null) {
                viewModelTask.getMyTask(user.getUser_id()).observe(requireActivity(), observeViewModelTask);
            }
        }
    };

    private Observer<List<Task>> observeViewModelTask = new Observer<List<Task>>() {
        @Override
        public void onChanged(List<Task> tasks) {
            if (tasks != null) {
//                adapterTask.setTaskList(tasks);
//                adapterTask.notifyDataSetChanged();
//                rvTask.setAdapter(adapterTask);
            }
        }
    };
}