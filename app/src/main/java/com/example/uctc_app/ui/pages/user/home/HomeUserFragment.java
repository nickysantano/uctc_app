package com.example.uctc_app.ui.pages.user.home;

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

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.login.LoginViewModel;
import com.example.uctc_app.ui.pages.user.program_list.ProgramAdapter;
import com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeUserFragment extends Fragment {

    @BindView(R.id.rv_recent_program_user)
    RecyclerView rvRecentProgram;

    private RecentEventAdapter adapter;
    private ProgramViewModel viewModel;
    private SharedPreferenceHelper helper;

    public HomeUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d("Hello","In the java");
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        viewModel = ViewModelProviders.of(requireActivity()).get(ProgramViewModel.class);
        viewModel.init(helper.getAccessToken());
        viewModel.getPrograms().observe(requireActivity(), observeViewModel);

        rvRecentProgram.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new RecentEventAdapter(getActivity());
    }

    private Observer<List<Program>> observeViewModel = new Observer<List<Program>>() {
        @Override
        public void onChanged(List<Program> programs) {
            if (programs != null) {
                adapter.setEventList(programs);
                adapter.notifyDataSetChanged();
                rvRecentProgram.setAdapter(adapter);
            }
        }
    };
}