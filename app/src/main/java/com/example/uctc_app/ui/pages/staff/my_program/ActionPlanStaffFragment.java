package com.example.uctc_app.ui.pages.staff.my_program;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanAdapter;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentArgs;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionPlanStaffFragment extends Fragment {

    @BindView(R.id.rv_action_plan_user)
    RecyclerView rvAction;

    private ActionPlanViewModel viewModel;
    private ActionPlanStaffAdapter adapter;
    private String program_id;
    private SharedPreferenceHelper helper;

    public ActionPlanStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_action_plan_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("Hello","In the java");
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        program_id = ActionPlanFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ActionPlanViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getActionPlans( Integer.parseInt(program_id)).observe(requireActivity(), observeViewModel); //untuk ambil argument navigation

        rvAction.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ActionPlanStaffAdapter(getActivity());
    }
    private Observer<List<ActionPlan>> observeViewModel = new Observer<List<ActionPlan>>() {
        @Override
        public void onChanged(List<ActionPlan> actionPlans) {
            if (actionPlans != null) {
                adapter.setActionList(actionPlans, program_id);
                adapter.notifyDataSetChanged();
                rvAction.setAdapter(adapter);
            }
        }
    };
}