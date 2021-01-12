package com.example.uctc_app.ui.pages.staff.program_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;

import butterknife.OnClick;

public class ProgramStaffFragment extends Fragment {

    public ProgramStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_program_staff, container, false);
    }

    @OnClick({R.id.btn_add_program_list_staff})
    public void onClick(View view) {
//        NavDirections action = ProgramUserFragmentDirections.actionNavProgramUserToAddProgramStaffFragment();
//        Navigation.findNavController(view).navigate(action);
    }
}