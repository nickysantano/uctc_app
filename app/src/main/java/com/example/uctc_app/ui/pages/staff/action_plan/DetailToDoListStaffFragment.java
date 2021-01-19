package com.example.uctc_app.ui.pages.staff.action_plan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.profile.ProfileStaffViewModel;
import com.example.uctc_app.ui.pages.staff.program_list.DetailProgramStaffFragmentArgs;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramViewModel;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailToDoListStaffFragment extends Fragment {

    @BindView(R.id.lbl_to_do_list_title)
    TextView titleTask;

    @BindView(R.id.lbl_to_do_list_description)
    TextView titleDescription;

    @BindView(R.id.lbl_pic_task)
    TextView picTask;

    @BindView(R.id.lbl_due_date_task)
    TextView dateTask;

    private TaskStaffViewModel viewModel;
    private ProfileStaffViewModel viewModelProfile;
    private SharedPreferenceHelper helper;
    Task task;

    public DetailToDoListStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_to_do_list_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle("Detail Task");

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TaskStaffViewModel.class);
        viewModel.init(helper.getAccessToken());

        viewModelProfile = ViewModelProviders.of(requireActivity()).get(ProfileStaffViewModel.class);
        viewModelProfile.init(helper.getAccessToken());
        viewModelProfile.getUsers().observe(requireActivity(), profileObserver);

        if (getArguments() != null){
            task = DetailToDoListStaffFragmentArgs.fromBundle(getArguments()).getDetailTask();
            initDetailTask(task);
        }
    }

    private void initDetailTask(Task task) {
        titleTask.setText(task.getName());
        titleDescription.setText(task.getDescription());
        dateTask.setText(task.getDate());
    }

    private Observer<List<User>> profileObserver = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            if (users!=null){
                for (int i = 0 ; i < users.size();i++){
                    if(users.get(i).getUser_id()==task.getPic()){
                        picTask.setText(users.get(i).getName());
                        break;
                    }
                }
            }
        }
    };

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
