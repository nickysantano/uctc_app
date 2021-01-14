package com.example.uctc_app.ui.pages.user.my_program;

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
import android.widget.ProgressBar;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.model.local.role.User;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.profile.ProfileUserViewModel;
import com.example.uctc_app.ui.pages.user.program_list.ProgramAdapter;
import com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProgramUserFragment extends Fragment {
    @BindView(R.id.progressBar)
    ProgressBar loading;

    @BindView(R.id.rv_my_program_user)
    RecyclerView rvMyProgram;

    public String getId;

    private MyProgramViewModel viewModel;
    private ProfileUserViewModel profileViewModel;
    private MyProgramUserAdapter adapter;
    private SharedPreferenceHelper helper;

    public MyProgramUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_program_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        showLoading(true);
        Log.d("Hello","In the java");
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        helper = SharedPreferenceHelper.getInstance(requireActivity());

        profileViewModel = ViewModelProviders.of(requireActivity()).get(ProfileUserViewModel.class);
        profileViewModel.init(helper.getAccessToken());
        profileViewModel.getUser().observe(requireActivity(), observeProfileViewModel);


        rvMyProgram.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyProgramUserAdapter(getActivity());

    }

    private Observer<User> observeProfileViewModel = new Observer<User>() {

        @Override
            public void onChanged(User user) {
                Log.d("haiiiiiiiiiiiiii", "wowowowowowowow");
                getId = "" + user.getUser_id();
                if (getId != null){
                    Log.d("haiiiiiiiiiiiiii", "wowowowowowowow : " + getId);
                    viewModel = ViewModelProviders.of(requireActivity()).get(MyProgramViewModel.class);
                    viewModel.init(helper.getAccessToken());
                    viewModel.myPrograms(getId).observe(requireActivity(), observeViewModel);
                }
            }

    };

    private Observer<List<Program>> observeViewModel = new Observer<List<Program>>() {
        @Override
        public void onChanged(List<Program> programs) {
            if (programs != null) {
                adapter.setEventList(programs);
                adapter.notifyDataSetChanged();
                rvMyProgram.setAdapter(adapter);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvMyProgram.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rvMyProgram.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}