package com.example.uctc_app.ui.pages.staff.action_plan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uctc_app.R;

public class CommiteeListStaffFragment extends Fragment {

    public CommiteeListStaffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_commitee_adapter, container, false);
    }
}