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
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.ActionPlan;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanFragmentArgs;
import com.example.uctc_app.ui.pages.user.my_program.ActionPlanViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionPlanStaffFragment extends Fragment {

    public ActionPlanStaffFragment() {
    }

    @BindView(R.id.progressBar_cover)
    ImageView loading_cover;

    @BindView(R.id.progressBar)
    LottieAnimationView loading;

    @BindView(R.id.rv_action_plan_staff)
    RecyclerView rvAction;

    @BindView(R.id.lbl_program_name_staff)
    TextView programName;

    private ActionPlanViewModel viewModel;
    private com.example.uctc_app.ui.pages.staff.action_plan.ActionPlanAdapter adapter;
    private SharedPreferenceHelper helper;
    private String program_id;
    private int actionPlan_id;

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
        showLoading(true);

        program_id = ActionPlanFragmentArgs.fromBundle(getArguments()).getProgramId();

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ActionPlanViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getActionPlans(Integer.parseInt(program_id)).observe(requireActivity(), observeViewModel); //untuk ambil argument navigation

        rvAction.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ActionPlanAdapter(getActivity());
    }

    @OnClick({R.id.btn_add_action_plan})
    public void onClick(View view) {
        NavDirections action = ActionPlanStaffFragmentDirections.actionActionPlanStaffToAddActionPlan(program_id);
        Navigation.findNavController(view).navigate(action);
    }

    private void initDetailProgram(Program program) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle("Detail Program");
        programName.setText(program.getName());
//        picTask.setText(task.getPic());
    }

//    private Observer<Program> observer = new Observer<Program>() {
//        @Override
//        public void onChanged(Program program) {
//            programName.setText(program.getName());
//        }
//    };

    private Observer<List<ActionPlan>> observeViewModel = new Observer<List<ActionPlan>>() {
        @Override
        public void onChanged(List<ActionPlan> actionPlans) {
            if (actionPlans != null) {
                adapter.setActionList(actionPlans, program_id);
                adapter.notifyDataSetChanged();
                rvAction.setAdapter(adapter);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvAction.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            loading_cover.setVisibility(View.VISIBLE);
        } else {
            rvAction.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            loading_cover.setVisibility(View.GONE);
        }
    }
}