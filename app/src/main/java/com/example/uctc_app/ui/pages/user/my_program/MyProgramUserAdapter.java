package com.example.uctc_app.ui.pages.user.my_program;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uctc_app.R;
import com.example.uctc_app.model.local.role.Program;
import com.example.uctc_app.repository.login.ProgramRepository;
import com.example.uctc_app.ui.MainActivity;
import com.example.uctc_app.ui.pages.user.program_list.ProgramUserFragmentDirections;
import com.example.uctc_app.ui.pages.user.program_list.ProgramViewModel;
import com.example.uctc_app.utils.SharedPreferenceHelper;

import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

public class MyProgramUserAdapter extends RecyclerView.Adapter<MyProgramUserAdapter.ViewHolder> {


    private Context context;
    private List<Program> myProgramList;

    public MyProgramUserAdapter(Context context) {
        this.context = context;
    }

    public void setEventList(List<Program> myProgramList) {
        this.myProgramList = myProgramList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyProgramUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_my_program_user_adapter, parent, false);
        return new MyProgramUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProgramUserAdapter.ViewHolder holder, int position) {
        Program program = myProgramList.get(position);
        Log.d("My Program List: ", "" + program.getName());

        holder.name.setText(program.getName());
        holder.date.setText("Date: " + program.getDate());

        holder.itemView.setOnClickListener(view -> {
            MyProgramUserFragmentDirections.ActionMyProgramUserToDetailProgram actionMyProgramUserToDetailProgram =
                    MyProgramUserFragmentDirections.actionMyProgramUserToDetailProgram(program);
            Navigation.findNavController(view).navigate(actionMyProgramUserToDetailProgram);
        });
    }

    @Override
    public int getItemCount() {
        return myProgramList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lbl_name_my_program_user);
            date = itemView.findViewById(R.id.lbl_date_my_program_user);
        }
    }

}