package com.example.uctc_app.ui.pages.user.my_program;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.adapter.TaskAdapter;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskFragment extends Fragment {

    @BindView(R.id.rv_task_list)
    RecyclerView rvTask;

    private TaskViewModel viewModel;
    private TaskAdapter adapter;
    private SharedPreferenceHelper helper;

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
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(TaskViewModel.class);
        viewModel.init(helper.getAccessToken());
//        viewModel.getTask(id).observe(requireActivity(), observeViewModel);

        rvTask.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TaskAdapter(getActivity());
    }

    @OnClick({R.id.btnPic})
    public void onClick(View view) {
        //
    }

    private Observer<List<Task>> observeViewModel = new Observer<List<Task>>() {
        @Override
        public void onChanged(List<Task> tasks) {
            if (tasks != null) {
                adapter.setTaskList(tasks);
                adapter.notifyDataSetChanged();
                //recycle
            }
        }
    };

}