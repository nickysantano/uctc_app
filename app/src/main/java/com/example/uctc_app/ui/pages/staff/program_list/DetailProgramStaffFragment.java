package com.example.uctc_app.ui.pages.staff.program_list;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Documentation;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.Task;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.staff.documentation.DocumentationAdapter;
import com.example.uctc_app.ui.pages.staff.documentation.DocumentationViewModel;
import com.example.uctc_app.ui.pages.user.my_program.MyProgramUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.utils.Constants;
import com.example.uctc_app.utils.SharedPreferenceHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailProgramStaffFragment extends Fragment {
    @BindView(R.id.lbl_cover_img)
    ImageView imgProgram;

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

    @BindView(R.id.rv_documentation)
    RecyclerView rvDocumentation;

    @BindView(R.id.btn_detail_program_action_plan_program)
    FloatingActionButton toActionPlan;

    private ProgramViewModel viewModel;
    private DocumentationViewModel viewModelDocumentation;
    private ProfileUserViewModel viewModelProfile;
    private SharedPreferenceHelper helper;
    private DocumentationAdapter adapterDocumentation;
    List<User> committeeList;
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

        program = DetailProgramStaffFragmentArgs.fromBundle(getArguments()).getDetailProgramStaff();
        initDetailProgram(program);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ProgramViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getCommittees(program.getProgram_id()).observe(requireActivity(),programObserver);

        viewModelDocumentation = ViewModelProviders.of(requireActivity()).get(DocumentationViewModel.class);
        viewModelDocumentation.init(helper.getAccessToken());
        viewModelDocumentation.documentation(program.getProgram_id()).observe(requireActivity(), documentationObserver);

        rvDocumentation.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapterDocumentation = new DocumentationAdapter(getActivity());
    }
    private Observer <List<User>> programObserver = new Observer<List<User>>() {
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

    private Observer<List<Documentation>> documentationObserver = new Observer<List<Documentation>>() {
        @Override
        public void onChanged(List<Documentation> documentations) {
            if (documentations != null) {
                adapterDocumentation.setDocumentationData(documentations);
                adapterDocumentation.notifyDataSetChanged();
                rvDocumentation.setAdapter(adapterDocumentation);
            }
        }
    };

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
        Glide.with(getActivity()).load(Constants.BASE_IMAGE_PROGRAMS_URL + program.getThumbnail()).into(imgProgram);
        titleProgram.setText(program.getName());
        descriptionProgram.setText(program.getDescription());
        goalProgram.setText(program.getGoal());

        if (program.getStatus().equalsIgnoreCase("0")){
            statusProgram.setText("Pending");
        }else if(program.getStatus().equalsIgnoreCase("1")){
            statusProgram.setText("On-going");
        }else if(program.getStatus().equalsIgnoreCase("2")){
            statusProgram.setText("Finished");
        }else{
            statusProgram.setText("Suspended");
        }

        Log.d("THIS IS STATUS: ", program.getStatus());
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