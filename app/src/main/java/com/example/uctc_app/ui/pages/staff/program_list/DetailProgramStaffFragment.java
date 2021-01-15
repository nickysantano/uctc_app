package com.example.uctc_app.ui.pages.staff.program_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.my_program.MyProgramUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProgramStaffFragment extends Fragment {
    @BindView(R.id.lbl_title_program_user)
    TextView titleProgram;

    @BindView(R.id.lbl_description_program_user)
    TextView descriptionProgram;

    @BindView(R.id.lbl_goal_program)
    TextView goalProgram;

    @BindView(R.id.lbl_status_program)
    TextView statusProgram;

    @BindView(R.id.lbl_date_program)
    TextView dateProgram;

    private DetailStaffViewModel viewModel;
    private SharedPreferenceHelper helper;
    Program program;

    public DetailProgramStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_program_staff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(DetailStaffViewModel.class);
        viewModel.init(helper.getAccessToken());

        if (getArguments() != null){
            program = DetailProgramStaffFragmentArgs.fromBundle(getArguments()).getDetailProgramStaff();
            initDetailProgram(program);
        }

    }

    @OnClick({R.id.btn_detail_program_action_plan_program})
    public void onClick(View view) {
        DetailProgramStaffFragmentDirections.ActionDetailProgramStaffToActionPlan actionDetailProgramStaffToActionPlan =
                DetailProgramStaffFragmentDirections.actionDetailProgramStaffToActionPlan(program.getProgram_id() + "");
        Navigation.findNavController(view).navigate(actionDetailProgramStaffToActionPlan);
    }

//    holder.itemView.setOnClickListener(view -> {
//        MyProgramUserFragmentDirections.ActionMyProgramUserToActionPlan actionMyProgramUserToActionPlan =
//                MyProgramUserFragmentDirections.actionMyProgramUserToActionPlan(program.getProgram_id() + "");
//        Navigation.findNavController(view).navigate(actionMyProgramUserToActionPlan);
//    });


    private void initDetailProgram(Program program) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle("Detail Program");
        titleProgram.setText(program.getName());
        descriptionProgram.setText(program.getDescription());
        goalProgram.setText(program.getGoal());
        statusProgram.setText(program.getStatus());
        dateProgram.setText(program.getDate());
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