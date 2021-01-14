package com.example.uctc_app.ui.pages.staff.program_list;

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
import android.widget.ProgressBar;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.program_list.ProgramAdapter;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgramStaffFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar loading;

    @BindView(R.id.rv_program_list)
    RecyclerView rvProgram;

    private com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel viewModel;
    private ProgramAdapter adapter;
    private SharedPreferenceHelper helper;

    public ProgramStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_program_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        Log.d("Hello","In the java");
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ProgramViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getPrograms().observe(requireActivity(), observeViewModel);

        rvProgram.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProgramAdapter(getActivity());
    }

    @OnClick({R.id.btn_add_program})
    public void onClick(View view) {
        NavDirections action = ProgramUserFragmentDirections.actionNavProgramUserToAddProgramStaffFragment();
        Navigation.findNavController(view).navigate(action);
    }

    private Observer<List<Program>> observeViewModel = new Observer<List<Program>>() {
        @Override
        public void onChanged(List<Program> programs) {
            if (programs != null) {
                adapter.setEventList(programs);
                adapter.notifyDataSetChanged();
                rvProgram.setAdapter(adapter);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvProgram.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rvProgram.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}