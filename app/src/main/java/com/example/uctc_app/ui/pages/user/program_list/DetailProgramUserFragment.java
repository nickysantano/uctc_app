package com.example.uctc_app.ui.pages.user.program_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.program_list.DetailProgramStaffFragmentArgs;
import com.example.uctc_app.ui.pages.staff.program_list.DetailProgramStaffFragmentDirections;
import com.example.uctc_app.ui.pages.staff.program_list.ProgramViewModel;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProgramUserFragment extends Fragment {
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

    @BindView(R.id.btn_detail_program_action_plan_program)
    FloatingActionButton toActionPlan;

    private ProgramViewModel viewModelProgram;
    private DetailUserViewModel viewModel;
    private ProfileUserViewModel viewModelProfile;
    private SharedPreferenceHelper helper;
    List<User> committeeList;
    Program program;

    public DetailProgramUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_program_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        program = DetailProgramUserFragmentArgs.fromBundle(getArguments()).getDetailProgramUser();
        initDetailProgram(program);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModelProgram = ViewModelProviders.of(requireActivity()).get(ProgramViewModel.class);
        viewModelProgram.init(helper.getAccessToken());
        viewModelProgram.getCommittees(program.getProgram_id()).observe(requireActivity(), programObserver);
    }

    @OnClick({R.id.btn_detail_program_action_plan_program})
    public void onClick(View view) {
        DetailProgramUserFragmentDirections.ActionDetailProgramUserToActionPlanFragment actionDetailProgramUserToActionPlanFragment =
                DetailProgramUserFragmentDirections.actionDetailProgramUserToActionPlanFragment(program.getProgram_id() + "");
        Navigation.findNavController(view).navigate(actionDetailProgramUserToActionPlanFragment);
    }

    private Observer<List<User>> programObserver = new Observer<List<User>>() {
        @Override
        public void onChanged(List<User> users) {
            if (users!=null){
                committeeList = users;
                viewModelProfile = ViewModelProviders.of(requireActivity()).get(ProfileUserViewModel.class);
                viewModelProfile.init(helper.getAccessToken());
                viewModelProfile.getUser().observe(requireActivity(),userObserver);
            }
        }
    };

    private Observer<User> userObserver = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            if (user !=null){
                for (int i = 0 ; i < committeeList.size();i++){
                    if (committeeList.get(i).getUser_id() == user.getUser_id()){
                        //disable button
                        toActionPlan.setVisibility(View.VISIBLE);
                        toActionPlan.setEnabled(true);
                    }
                }
            }
        }
    };

    private void initDetailProgram(Program program) {
        Objects.requireNonNull((MainActivity) requireActivity()).getSupportActionBar().setTitle("Detail Program");
        titleProgram.setText(program.getName());
        descriptionProgram.setText(program.getDescription());
        goalProgram.setText(program.getDescription());
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