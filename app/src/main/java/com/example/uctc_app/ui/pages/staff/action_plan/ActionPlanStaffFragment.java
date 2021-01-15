package com.example.uctc_app.ui.pages.staff.action_plan;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.repository.login.ProfileRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.program_list.DetailProgramStaffFragmentDirections;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramStaffFragment;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramStaffFragmentDirections;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanAdapter;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentArgs;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanViewModel;
import com.example.uctc_app.ui.pages.user.program_list.AddProgramStaffFragmentDirections;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionPlanStaffFragment extends Fragment {

    public ActionPlanStaffFragment() {
    }
    @BindView(R.id.rv_action_plan_staff)
    RecyclerView rvAction;

    @BindView(R.id.lbl_program_name_staff)
    TextView programName;

//    @BindView(R.id.btn_program_add_action_plan_staff)
//    FloatingActionButton btnAddActionPlan;

    private ActionPlanViewModel viewModel;
    private com.example.uctc_app.ui.pages.user.my_program.ActionPlanAdapter adapter;
    private SharedPreferenceHelper helper;

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

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ActionPlanViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getActionPlans( Integer.parseInt(ActionPlanFragmentArgs.fromBundle(getArguments()).getProgramId())).observe(requireActivity(), observeViewModel); //untuk ambil argument navigation

        rvAction.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ActionPlanAdapter(getActivity());

    }



    private Observer<ActionPlan> observer = new Observer<ActionPlan>() {
        @Override
        public void onChanged(ActionPlan actionPlan) {
            programName.setText(actionPlan.getName());
        }

    };

    private Observer<List<ActionPlan>> observeViewModel = new Observer<List<ActionPlan>>() {
        @Override
        public void onChanged(List<ActionPlan> actionPlans) {
            if (actionPlans != null) {
                adapter.setActionList(actionPlans);
                adapter.notifyDataSetChanged();
                rvAction.setAdapter(adapter);
            }
        }
    };
}